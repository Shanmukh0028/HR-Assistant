package com.payu.hrassistant.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatMessageInfo {
    @JsonProperty
    private String message;
    @JsonProperty
    private String sender;
    @JsonProperty
    private String sentAt;
}
