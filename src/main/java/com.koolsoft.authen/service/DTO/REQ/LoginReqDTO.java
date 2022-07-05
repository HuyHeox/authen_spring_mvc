package com.koolsoft.authen.service.DTO.REQ;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginReqDTO implements Serializable {
    private String username;
    private String password;
}
