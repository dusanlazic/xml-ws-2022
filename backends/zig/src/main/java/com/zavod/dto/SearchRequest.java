package com.zavod.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "search_request")
public class SearchRequest {

    private List<String> query;

    public SearchRequest() {
    }

    public SearchRequest(List<String> query) {
        this.query = query;
    }

    @XmlElement(name="query")
    public List<String> getQuery() {
        return query;
    }

    public void setQuery(List<String> query) {
        this.query = query;
    }
}
