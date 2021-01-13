package com.dolap.quarkus.ddd.domain.member.exception;

public class BusinessException extends RuntimeException {

    private final String messageKey;

    public BusinessException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

}
