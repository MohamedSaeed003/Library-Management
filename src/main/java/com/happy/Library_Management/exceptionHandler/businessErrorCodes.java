package com.happy.Library_Management.exceptionHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum businessErrorCodes {

    NO_CODE(0, NOT_IMPLEMENTED, "No code"),

    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Incorrect current password"),

    INCORRECT_NEW_PASSWORD(301, BAD_REQUEST, "The new password is not match"),

    ACCOUNT_LOCKED(302, FORBIDDEN, "Account locked"),

    ACCOUNT_DISABLED(303, FORBIDDEN, "User disabled"),

    BAD_CREDENTIALS(304, FORBIDDEN, "Email or Password is invalid"),
    ;

    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    businessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
