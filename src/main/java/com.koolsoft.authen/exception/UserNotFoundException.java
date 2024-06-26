package com.koolsoft.authen.exception;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String userName) {
        super(ServiceError.USER_NOT_FOUND_EXCEPTION, null, buildSingleParamMaps("userName", userName));
    }
}
