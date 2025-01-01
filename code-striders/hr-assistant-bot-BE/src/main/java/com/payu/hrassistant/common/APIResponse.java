package com.payu.hrassistant.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class APIResponse {
    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("status_msg")
    private String statusMsg;

    @JsonProperty("error")
    private String error;

    @JsonProperty("response")
    Object response;
}
