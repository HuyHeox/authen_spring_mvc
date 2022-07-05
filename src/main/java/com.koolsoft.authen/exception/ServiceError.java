package com.koolsoft.authen.exception;

import lombok.Getter;

@Getter
public enum ServiceError {
    PASSWORD_NOT_MATCH(401, "err.authorize.password-not-match"),
    USER_NOT_FOUND_EXCEPTION(401, "err.api.user-not-found"),
    USER_ALREADY_EXIST(400, "err.api.user-already-exist");
   ServiceError(int errCode, String messageKey) {
        this.errCode = errCode;
        this.messageKey = messageKey;
    }

    private final int errCode;
    private final String messageKey;
}
