package com.payu.hrassistant.usermanagement.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class LoginResponse {
    @JsonProperty("userId")
    Long userId;

    @JsonProperty("role")
    String role;

}
