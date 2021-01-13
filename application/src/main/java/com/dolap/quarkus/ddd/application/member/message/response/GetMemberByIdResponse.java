package com.dolap.quarkus.ddd.application.member.message.response;

import com.dolap.quarkus.ddd.application.member.message.dto.MemberDTO;

public class GetMemberByIdResponse {

    private final MemberDTO member;

    public GetMemberByIdResponse(MemberDTO member) {
        this.member = member;
    }

    public MemberDTO getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "GetMemberByIdResponse{" +
                "member=" + member +
                '}';
    }
}
