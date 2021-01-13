package com.dolap.quarkus.ddd.domain.member.service


import com.dolap.quarkus.ddd.domain.member.vo.PhoneNumber
import com.dolap.quarkus.ddd.domain.member.entity.Member
import com.dolap.quarkus.ddd.domain.member.exception.MemberNotFoundException
import com.dolap.quarkus.ddd.domain.member.repository.MemberRepository
import spock.lang.Specification

class UpdateMemberServiceSpec extends Specification {

    private UpdateMemberService updateMemberService
    private MemberRepository memberRepository

    def "setup"() {
        memberRepository = Mock(MemberRepository)
        updateMemberService = new UpdateMemberService(memberRepository)
    }

    def "should throw MemberNotFoundException when member with given id does not exists in the repository"() {
        given:
        def memberId = 1L
        def username = "updatedUsername"
        def phoneCountryCode = "updatedCountryCode"
        def phoneNumber = "updatedNumber"

        when:
        updateMemberService.update(memberId, username, phoneCountryCode, phoneNumber)

        then:
        1 * memberRepository.findMemberById(memberId) >> Optional.empty()
        0 * _
        thrown MemberNotFoundException
    }

    def "should update member successfully"() {
        given:
        def memberId = 1L
        def username = "updatedUsername"
        def phoneCountryCode = "updatedCountryCode"
        def phoneNumber = "updatedNumber"

        def existingMember = new Member()
        existingMember.id = memberId
        existingMember.username = "username"
        existingMember.phoneNumber = PhoneNumber.of("countryCode", "number")

        when:
        Member updatedMember = updateMemberService.update(memberId, username, phoneCountryCode, phoneNumber)

        then:
        1 * memberRepository.findMemberById(memberId) >> Optional.of(existingMember)
        1 * memberRepository.save(_ as Member)
        0 * _

        existingMember == updatedMember
        existingMember.username == username
        existingMember.phoneNumber.countryCode == phoneCountryCode
        existingMember.phoneNumber.number == phoneNumber
    }

}
