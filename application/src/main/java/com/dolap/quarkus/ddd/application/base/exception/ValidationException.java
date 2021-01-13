package com.dolap.quarkus.ddd.application.base.exception;

public class ValidationException extends RuntimeException {

    private final String messageKey;

    public ValidationException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

}
