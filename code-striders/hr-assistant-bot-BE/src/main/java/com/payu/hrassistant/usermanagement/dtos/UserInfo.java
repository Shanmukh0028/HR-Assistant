package com.payu.hrassistant.usermanagement.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfo {
    private Long id;
    private String username;

}
