package com.dolap.quarkus.ddd.domain.member.exception;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException() {
        super("error.member.not.found");
    }

}
