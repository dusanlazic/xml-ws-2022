package com.zavod.service;

import com.zavod.dto.MetaSearchQuery;
import com.zavod.dto.MetaSearchRequest;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.ZahtevRepository;
import com.zavod.repository.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MetadataService {

	@Autowired
	public ZahtevRepository zahtevRepository;

	@Autowired
	public MetadataRepository metadataRepository;

	String graphName = "<http://localhost:8080/fuseki-zig/ZahteviDataset/data/zahtevi/metadata>";
	String namespace = "http://www.zavod.com/Zig/pred/";


	public List<Zahtev> metaSearch(MetaSearchRequest request) throws XMLDBException {
		prepreocessMetaSearchRequest(request);
		String query = this.buildMetaSearchQuery(request, graphName, "select");
		List<String> ids = metadataRepository.executeSparqlQuery(query);
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

		query.append(queryType + " ?subject FROM ").append(graphName).append(" \nWHERE {  \n");
		query.append("\t?subject ?predicate ?object . \n");
		for (String predicate : predicates) {
			query.append("\t?subject <").append(namespace).append(predicate).append("> ?").append(predicate).append(" . \n");
		}
		query.append("\tFILTER (");
		for (MetaSearchQuery metaSearchQuery : request.getQuery()) {
			query.append(" ( ?").append(metaSearchQuery.getPredicate()).append(metaSearchQuery.getRelation())
				 .append(metaSearchQuery.getObject()).append(" ) ");
			if (request.getQuery().indexOf(metaSearchQuery) != request.getQuery().size() - 1) {
				query.append(metaSearchQuery.getOperator());
			}
		}
		query.append(") \n}\n");
		query.append("GROUP BY ?subject");
		return query.toString();
	}

	public Set<String> getUniquePredicates(MetaSearchRequest request) {
		Set<String> result = new HashSet<>();
		for (MetaSearchQuery query : request.getQuery()) {
			result.add(query.getPredicate());
		}
		return result;
	}

	public void exportToRDF(String brojPrijave) {
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
		metadataRepository.executeDescribeQuery(queryStr, System.out);
	}


	public void exportToJSON(String brojPrijave) {
		String queryStr =
						"select ?subject ?predicate ?object FROM "+ graphName + " \n" +
						"WHERE {  \n" +
						"\t?subject ?predicate ?object . \n" +
						"\t?subject <http://www.zavod.com/Zig/pred/Broj_prijave> ?Broj_prijave . \n" +
						"\tFILTER ( ( ?Broj_prijave='"+ brojPrijave +"' ) ) \n" +
						"}";
		metadataRepository.executeSelectQuery(queryStr, System.out);
	}
}
