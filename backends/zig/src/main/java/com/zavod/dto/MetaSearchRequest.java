package com.zavod.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "meta_search_request")
public class MetaSearchRequest {

    private List<MetaSearchQuery> query;

    public MetaSearchRequest() {
    }

    public MetaSearchRequest(List<MetaSearchQuery> query) {
        this.query = query;
    }

    @XmlElement(name="query")
    public List<MetaSearchQuery> getQuery() {
        return query;
    }

    public void setQuery(List<MetaSearchQuery> query) {
        this.query = query;
    }
}

