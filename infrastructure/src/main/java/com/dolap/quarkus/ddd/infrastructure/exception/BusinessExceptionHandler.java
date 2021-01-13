package com.dolap.quarkus.ddd.infrastructure.exception;

import com.dolap.quarkus.ddd.domain.member.exception.BusinessException;
import com.dolap.quarkus.ddd.infrastructure.configuration.MessagePropertyReader;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionHandler implements ExceptionMapper<BusinessException> {

    @Inject
    MessagePropertyReader messagePropertyReader;

    @Override
    public Response toResponse(BusinessException exception) {
        String errorMessage = messagePropertyReader.getMessage(exception.getMessageKey());
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
        return Response.status(Status.BAD_REQUEST).entity(errorResponse).build();
    }

}
