package com.zavod.service;

import com.zavod.dto.MetaSearchQuery;
import com.zavod.dto.MetaSearchRequest;
import com.zavod.model.Zahtev;
import com.zavod.repository.KorisniciRepository;
import com.zavod.repository.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MetadataService {

	@Autowired
	public KorisniciRepository zigRepository;

	@Autowired
	public MetadataRepository metadataRepository;

	public List<Zahtev> metaSearch(MetaSearchRequest request) throws XMLDBException {
		String graphName = "<http://localhost:8080/fuseki-zig/ZahteviDataset/data/zahtevi/metadata>";
		request = prepreocessMetaSearchRequest(request);
		String query = this.buildMetaSearchQuery(request, graphName);
		List<String> ids = metadataRepository.executeSparqlQuery(query);
		return zigRepository.findByIds(ids);
	}

	private MetaSearchRequest prepreocessMetaSearchRequest(MetaSearchRequest request) {
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
		return request;
	}

	public String buildMetaSearchQuery(MetaSearchRequest request, String graphName) {
		StringBuilder query = new StringBuilder();
		Set<String> predicates = getUniquePredicates(request);
		String namespace = "http://www.zavod.com/Zig/pred/";
		query.append("SELECT ?subject FROM ").append(graphName).append(" \nWHERE {  \n");
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
}
