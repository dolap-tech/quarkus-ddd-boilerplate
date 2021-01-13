package com.dolap.quarkus.ddd.application.member.manager;

import com.dolap.quarkus.ddd.application.member.converter.MemberToMemberDTOConverter;
import com.dolap.quarkus.ddd.application.member.message.dto.MemberDTO;
import com.dolap.quarkus.ddd.application.member.message.request.CreateMemberRequest;
import com.dolap.quarkus.ddd.application.member.message.request.UpdateMemberRequest;
import com.dolap.quarkus.ddd.application.member.message.response.CreateMemberResponse;
import com.dolap.quarkus.ddd.application.member.message.response.DeleteMemberResponse;
import com.dolap.quarkus.ddd.application.member.message.response.GetMemberByIdResponse;
import com.dolap.quarkus.ddd.application.member.message.response.GetMembersResponse;
import com.dolap.quarkus.ddd.application.member.message.response.UpdateMemberResponse;
import com.dolap.quarkus.ddd.application.member.validator.CreateMemberRequestValidator;
import com.dolap.quarkus.ddd.application.member.validator.UpdateMemberRequestValidator;
import com.dolap.quarkus.ddd.domain.member.entity.Member;
import com.dolap.quarkus.ddd.domain.member.service.CreateMemberService;
import com.dolap.quarkus.ddd.domain.member.service.DeleteMemberService;
import com.dolap.quarkus.ddd.domain.member.service.GetMemberService;
import com.dolap.quarkus.ddd.domain.member.service.UpdateMemberService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class MemberManager {

    private final GetMemberService getMemberService;
    private final CreateMemberService createMemberService;
    private final UpdateMemberService updateMemberService;
    private final DeleteMemberService deleteMemberService;
    private final MemberToMemberDTOConverter memberToMemberDTOConverter;
    private final CreateMemberRequestValidator createMemberRequestValidator;
    private final UpdateMemberRequestValidator updateMemberRequestValidator;

    @Inject
    public MemberManager(GetMemberService getMemberService, CreateMemberService createMemberService, UpdateMemberService updateMemberService,
                         DeleteMemberService deleteMemberService, MemberToMemberDTOConverter memberToMemberDTOConverter,
                         CreateMemberRequestValidator createMemberRequestValidator,
                         UpdateMemberRequestValidator updateMemberRequestValidator) {
        this.getMemberService = getMemberService;
        this.createMemberService = createMemberService;
        this.updateMemberService = updateMemberService;
        this.deleteMemberService = deleteMemberService;
        this.memberToMemberDTOConverter = memberToMemberDTOConverter;
        this.createMemberRequestValidator = createMemberRequestValidator;
        this.updateMemberRequestValidator = updateMemberRequestValidator;
    }

    public GetMemberByIdResponse getMemberById(Long id) {
        Member member = getMemberService.getById(id);
        return new GetMemberByIdResponse(memberToMemberDTOConverter.convert(member));
    }

    public GetMembersResponse getAllMembers() {
        List<Member> members = getMemberService.getAll();
        return new GetMembersResponse(memberToMemberDTOConverter.convert(members));
    }

    public UpdateMemberResponse updateMember(UpdateMemberRequest request) {
        updateMemberRequestValidator.validate(request);

        Member updatedMember = updateMemberService.update(request.getId(), request.getUsername(), request.getPhoneCountryCode(), request.getPhoneNumber());
        MemberDTO memberDTO = memberToMemberDTOConverter.convert(updatedMember);
        return new UpdateMemberResponse(memberDTO);
    }

    public CreateMemberResponse createMember(CreateMemberRequest request) {
        createMemberRequestValidator.validate(request);

        Member newMember = createMemberService.create(request.getUsername(), request.getPassword(), request.getPhoneCountryCode(), request.getPhoneNumber());
        MemberDTO memberDTO = memberToMemberDTOConverter.convert(newMember);
        return new CreateMemberResponse(memberDTO);
    }

    public DeleteMemberResponse deleteMember(Long id) {
        deleteMemberService.deleteMember(id);
        return new DeleteMemberResponse();
    }

}
