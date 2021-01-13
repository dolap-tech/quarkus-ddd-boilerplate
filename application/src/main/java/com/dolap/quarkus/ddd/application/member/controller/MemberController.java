package com.dolap.quarkus.ddd.application.member.controller;

import com.dolap.quarkus.ddd.application.member.message.request.CreateMemberRequest;
import com.dolap.quarkus.ddd.application.member.message.request.UpdateMemberRequest;
import com.dolap.quarkus.ddd.application.member.message.response.CreateMemberResponse;
import com.dolap.quarkus.ddd.application.member.message.response.DeleteMemberResponse;
import com.dolap.quarkus.ddd.application.member.message.response.GetMemberByIdResponse;
import com.dolap.quarkus.ddd.application.member.message.response.GetMembersResponse;
import com.dolap.quarkus.ddd.application.member.message.response.UpdateMemberResponse;

public interface MemberController {

    CreateMemberResponse createMember(CreateMemberRequest createMemberRequest);

    GetMemberByIdResponse getMemberById(Long id);

    GetMembersResponse getMembers();

    UpdateMemberResponse update(UpdateMemberRequest updateMemberRequest);

    DeleteMemberResponse deleteMemberById(Long id);
}
