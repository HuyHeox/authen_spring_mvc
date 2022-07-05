package com.koolsoft.authen.service.DTO.RES;

import com.koolsoft.authen.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResDTO {
    private User user;
    private String token;

    public LoginResDTO() {

    }
}
