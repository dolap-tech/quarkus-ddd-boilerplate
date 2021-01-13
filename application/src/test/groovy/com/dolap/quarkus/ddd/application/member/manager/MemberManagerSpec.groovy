package com.dolap.quarkus.ddd.application.member.manager


import com.dolap.quarkus.ddd.application.member.message.request.CreateMemberRequest
import com.dolap.quarkus.ddd.application.member.message.request.UpdateMemberRequest
import com.dolap.quarkus.ddd.application.member.message.response.CreateMemberResponse
import com.dolap.quarkus.ddd.application.member.message.response.DeleteMemberResponse
import com.dolap.quarkus.ddd.application.member.validator.CreateMemberRequestValidator
import com.dolap.quarkus.ddd.application.member.validator.UpdateMemberRequestValidator
import com.dolap.quarkus.ddd.domain.member.entity.Member
import com.dolap.quarkus.ddd.domain.member.service.CreateMemberService
import com.dolap.quarkus.ddd.domain.member.service.DeleteMemberService
import com.dolap.quarkus.ddd.domain.member.service.GetMemberService
import com.dolap.quarkus.ddd.domain.member.service.UpdateMemberService
import com.dolap.quarkus.ddd.application.member.converter.MemberToMemberDTOConverter
import com.dolap.quarkus.ddd.application.member.message.dto.MemberDTO
import com.dolap.quarkus.ddd.application.member.message.response.GetMemberByIdResponse
import com.dolap.quarkus.ddd.application.member.message.response.GetMembersResponse
import com.dolap.quarkus.ddd.application.member.message.response.UpdateMemberResponse
import spock.lang.Specification

class MemberManagerSpec extends Specification {

    private MemberManager memberManager
    private GetMemberService getMemberService
    private CreateMemberService createMemberService
    private UpdateMemberService updateMemberService
    private DeleteMemberService deleteMemberService
    private MemberToMemberDTOConverter memberToMemberDTOConverter
    private CreateMemberRequestValidator createMemberRequestValidator
    private UpdateMemberRequestValidator updateMemberRequestValidator


    def "setup"() {
        getMemberService = Mock(GetMemberService)
        createMemberService = Mock(CreateMemberService)
        updateMemberService = Mock(UpdateMemberService)
        deleteMemberService = Mock(DeleteMemberService)
        memberToMemberDTOConverter = Mock(MemberToMemberDTOConverter)
        createMemberRequestValidator = Mock(CreateMemberRequestValidator)
        updateMemberRequestValidator = Mock(UpdateMemberRequestValidator)
        memberManager = new MemberManager(getMemberService, createMemberService, updateMemberService,
                deleteMemberService, memberToMemberDTOConverter, createMemberRequestValidator,
                updateMemberRequestValidator)
    }

    def "should return GetMemberByIdResponse with given member id"() {
        given:
        def memberId = 1L
        def member = new Member()
        def memberDTO = MemberDTO.create(1L, "username", "phoneNumber")

        when:
        GetMemberByIdResponse response = memberManager.getMemberById(memberId)

        then:
        1 * getMemberService.getById(memberId) >> member
        1 * memberToMemberDTOConverter.convert(member) >> memberDTO
        0 * _

        response.member == memberDTO
    }

    def "should return GetMembersResponse successfully"() {
        given:
        def member1 = new Member()
        def member2 = new Member()
        def memberDTO1 = MemberDTO.create(1L, "username1", "phoneNumber1")
        def memberDTO2 = MemberDTO.create(2L, "username2", "phoneNumber2")

        when:
        GetMembersResponse response = memberManager.getAllMembers()

        then:
        1 * getMemberService.getAll() >> [member1, member2]
        1 * memberToMemberDTOConverter.convert([member1, member2]) >> [memberDTO1, memberDTO2]
        0 * _

        response.members[0] == memberDTO1
        response.members[1] == memberDTO2
    }

    def "should update member with given id and return UpdateMemberResponse"() {
        given:
        def request = new UpdateMemberRequest()
        request.id = 1L
        request.username = "updatedUsername"
        request.phoneCountryCode = "90"
        request.phoneNumber = "5554443322"

        def member = new Member()
        def memberDTO = MemberDTO.create(1L, "updatedUsername", "905554443322")

        when:
        UpdateMemberResponse response = memberManager.updateMember(request)

        then:
        1 * updateMemberRequestValidator.validate(request)
        1 * updateMemberService.update(request.id, request.username, request.phoneCountryCode, request.phoneNumber) >> member
        1 * memberToMemberDTOConverter.convert(member) >> memberDTO
        0 * _

        response.member == memberDTO
    }

    def "should create member and return CreateMemberResponse"() {
        given:
        def request = new CreateMemberRequest()
        request.username = "updatedUsername"
        request.password = "updatedUsername"
        request.phoneCountryCode = "90"
        request.phoneNumber = "5554443322"

        def member = new Member()
        def memberDTO = MemberDTO.create(1L, "username", "905554443322")

        when:
        CreateMemberResponse response = memberManager.createMember(request)

        then:
        1 * createMemberRequestValidator.validate(request)
        1 * createMemberService.create(request.username, request.password, request.phoneCountryCode, request.phoneNumber) >> member
        1 * memberToMemberDTOConverter.convert(member) >> memberDTO
        0 * _

        response.member == memberDTO
    }

    def "should delete member and return DeleteMemberResponse"() {
        given:
        def memberId = 1L
        def expectedResponse = new DeleteMemberResponse()

        when:
        DeleteMemberResponse response = memberManager.deleteMember(memberId)

        then:
        1 * deleteMemberService.deleteMember(memberId)
        0 * _

        response.errorCode == expectedResponse.errorCode
        response.errorMessage == expectedResponse.errorMessage
        response.status == expectedResponse.status
    }

}
