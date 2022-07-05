package com.koolsoft.authen.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JWTTokenClaim {

    private String username;
    private String password;
    private Date expiredDate;

}
