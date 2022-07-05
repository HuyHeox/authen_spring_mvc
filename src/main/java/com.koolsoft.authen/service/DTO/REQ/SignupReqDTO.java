package com.koolsoft.authen.service.DTO.REQ;

import lombok.Data;

@Data
public class SignupReqDTO {
    private String username;
    private String password;
    private String[] roles;
}
