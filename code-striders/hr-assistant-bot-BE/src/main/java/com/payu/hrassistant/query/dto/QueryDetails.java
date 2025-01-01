package com.payu.hrassistant.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payu.hrassistant.query.model.ChatMessage;
import com.payu.hrassistant.usermanagement.dtos.UserInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class QueryDetails {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String domain;
    @JsonProperty
    private String status;
    @JsonProperty
    private UserInfo assignee;
    @JsonProperty
    private UserInfo createdBy;
    @JsonProperty
    private String createdAt;
    @JsonProperty
    private String resolvedAt;
    @JsonProperty
    private List<ChatMessageInfo> chatMessagesInfo;

}
