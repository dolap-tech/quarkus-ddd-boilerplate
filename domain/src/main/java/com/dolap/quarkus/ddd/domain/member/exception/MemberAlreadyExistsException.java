package com.dolap.quarkus.ddd.domain.member.exception;

public class MemberAlreadyExistsException extends BusinessException {

    public MemberAlreadyExistsException() {
        super("error.member.already.exists");
    }

}
