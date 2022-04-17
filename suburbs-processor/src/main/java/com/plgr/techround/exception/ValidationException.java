package com.plgr.techround.exception;

import lombok.Data;

/**
 * Validation Exception class. Used in case of
 * validation failure.
 */
@Data
public class ValidationException extends Exception {

    private String errorCode;
    private String errorDescription;

    public ValidationException(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
