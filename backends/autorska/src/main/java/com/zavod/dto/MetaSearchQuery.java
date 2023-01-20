package com.zavod.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MetaSearchQuery {
    private String predicate;
    private String relation;
    private String object;
    private String operator;

}
