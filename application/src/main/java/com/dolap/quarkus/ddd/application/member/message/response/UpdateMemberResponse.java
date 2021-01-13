package com.dolap.quarkus.ddd.application.member.message.response;

import com.dolap.quarkus.ddd.application.base.message.Response;
import com.dolap.quarkus.ddd.application.member.message.dto.MemberDTO;

public class UpdateMemberResponse extends Response {

    private final MemberDTO member;

    public UpdateMemberResponse(MemberDTO member) {
        this.member = member;
    }

    public MemberDTO getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "UpdateMemberResponse{" +
                "member=" + member +
                '}';
    }
}
