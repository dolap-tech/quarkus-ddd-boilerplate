package com.dolap.quarkus.ddd.application.member.message.response;

import com.dolap.quarkus.ddd.application.member.message.dto.MemberDTO;

public class CreateMemberResponse {

    private final MemberDTO member;

    public CreateMemberResponse(MemberDTO member) {
        this.member = member;
    }

    public MemberDTO getMember() {
        return this.member;
    }

    @Override
    public String toString() {
        return "CreateMemberResponse{" +
                "member=" + member +
                '}';
    }
}
