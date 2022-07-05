package com.koolsoft.authen.service.DTO.RES;

import com.koolsoft.authen.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResDTO {
    private User user;
    private String token;
}
