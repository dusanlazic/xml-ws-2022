package com.zavod.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaSearchQuery {
    private String predicate;
    private String relation;
    private String object;
    private String operator;

}
