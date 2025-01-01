package com.payu.hrassistant.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class QueryInfo {
    @JsonProperty
    private Long Id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String status;
    @JsonProperty
    private String createdAt;
    @JsonProperty
    private String resolvedAt;
}
