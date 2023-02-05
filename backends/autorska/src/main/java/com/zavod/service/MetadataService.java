package com.zavod.service;

import com.zavod.dto.MetaSearchQuery;
import com.zavod.dto.MetaSearchRequest;
import com.zavod.model.resenje.StatusResenja;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MetadataService {

	@Autowired
	public ZahtevRepository zahtevRepository;

	@Autowired
	public MetadataRepository metadataRepository;

	String graphName = "<http://localhost:8080/fuseki-autorska/ZahteviDataset/data/zahtevi/metadata>";
	String namespace = "http://www.zavod.com/Autorska/pred/";

	Map<String, String> types = new HashMap<String, String>() {{
		put("Datum_podnosenja", "xsd:date");
	}};

	public List<Zahtev> metaSearch(MetaSearchRequest request) throws XMLDBException {
		return metaSearch(request, false);
	}

	public List<Zahtev> metaSearch(MetaSearchRequest request, boolean hideNeprihvaceni) throws XMLDBException {
		prepreocessMetaSearchRequest(request);
		String query = this.buildMetaSearchQuery(request, graphName, "select");
		List<String> ids = metadataRepository.executeSparqlQuery(query);
		if (hideNeprihvaceni)
			return zahtevRepository.findByIds(ids).stream()
					.filter(z -> z.getInformacijeZavoda().getStatusResenja().equals(StatusResenja.PRIHVACEN.toString()))
					.collect(Collectors.toList());
		return zahtevRepository.findByIds(ids);
	}

	private void prepreocessMetaSearchRequest(MetaSearchRequest request) {
		for (MetaSearchQuery query : request.getQuery()) {
			query.setObject("'"+query.getObject()+"'");
			switch (query.getOperator()) {
				case "I":
					query.setOperator(" && ");
					break;
				case "ILI":
					query.setOperator(" || ");
					break;
				case "NE":
					query.setOperator(" && !");
					break;
			}
		}
	}

	public String buildMetaSearchQuery(MetaSearchRequest request, String graphName, String queryType) {
		StringBuilder query = new StringBuilder();
		Set<String> predicates = getUniquePredicates(request);

		query.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n");
		query.append(queryType + " ?subject FROM ").append(graphName).append(" \nWHERE {  \n");
		query.append("\t?subject ?predicate ?object . \n");
		for (String predicate : predicates) {
			query.append("\t?subject <").append(namespace).append(predicate).append("> ?").append(predicate).append(" . \n");
		}
		resolveTypes(request);
		query.append("\tFILTER (");
		for (MetaSearchQuery metaSearchQuery : request.getQuery()) {
			query.append(" ( ").append(metaSearchQuery.getPredicate()).append(metaSearchQuery.getRelation())
					.append(metaSearchQuery.getObject()).append(" ) ");
			if (request.getQuery().indexOf(metaSearchQuery) != request.getQuery().size() - 1) {
				query.append(metaSearchQuery.getOperator());
			}
		}
		query.append(") \n}\n");
		query.append("GROUP BY ?subject");
		return query.toString();
	}

	private void resolveTypes(MetaSearchRequest request) {
		request.getQuery().forEach(q -> {
			if (types.containsKey(q.getPredicate())) {
				String predicate = q.getPredicate();
				q.setPredicate(types.get(predicate) + "(?" + predicate + ") ");
				q.setObject(types.get(predicate) + "(" + q.getObject() + ") ");
			} else {
				q.setPredicate("?" + q.getPredicate());
			}
		});
	}

	public Set<String> getUniquePredicates(MetaSearchRequest request) {
		Set<String> result = new HashSet<>();
		for (MetaSearchQuery query : request.getQuery()) {
			result.add(query.getPredicate());
		}
		return result;
	}

	public ResponseEntity<String> exportToRDF(String brojPrijave) {
		MetaSearchRequest req = new MetaSearchRequest();
		req.setQuery(new ArrayList<>());
		MetaSearchQuery query = new MetaSearchQuery();
		query.setPredicate("Broj_prijave");
		query.setRelation("=");
		query.setObject(brojPrijave);
		query.setOperator("I");
		req.getQuery().add(query);
		prepreocessMetaSearchRequest(req);
		String queryStr = this.buildMetaSearchQuery(req, graphName, "describe");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		metadataRepository.executeDescribeQuery(queryStr, baos);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_XML)
				.body(baos.toString());
	}


	public ResponseEntity<String> exportToJSON(String brojPrijave) {
		String queryStr =
						"select ?subject ?predicate ?object FROM "+ graphName + " \n" +
						"WHERE {  \n" +
						"\t?subject ?predicate ?object . \n" +
						"\t?subject <http://www.zavod.com/Autorska/pred/Broj_prijave> ?Broj_prijave . \n" +
						"\tFILTER ( ( ?Broj_prijave='"+ brojPrijave +"' ) ) \n" +
						"}";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		metadataRepository.executeSelectQuery(queryStr, baos);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(baos.toString());
	}
}
