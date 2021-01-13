package com.dolap.quarkus.ddd.application.member.message.response;

import com.dolap.quarkus.ddd.application.member.message.dto.MemberDTO;

import java.util.List;

public class GetMembersResponse {

    private final List<MemberDTO> members;

    public GetMembersResponse(List<MemberDTO> members) {
        this.members = members;
    }

    public List<MemberDTO> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "GetMembersResponse{" +
                "members=" + members +
                '}';
    }
}
