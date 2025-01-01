package com.payu.hrassistant.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QueryDto {
    @JsonProperty
    private Long queryId;
    @JsonProperty
    private String title;
    @JsonProperty
    private String question;
    @JsonProperty
    private Long createdById;
    @JsonProperty
    private Long assigneeId;
    @JsonProperty
    private String domain;
}
