package com.dolap.quarkus.ddd.infrastructure.exception;

import com.dolap.quarkus.ddd.application.base.message.Response;

public class ErrorResponse extends Response {

    public ErrorResponse(String message) {
        setStatus("ERROR");
        setErrorMessage(message);
    }

}
