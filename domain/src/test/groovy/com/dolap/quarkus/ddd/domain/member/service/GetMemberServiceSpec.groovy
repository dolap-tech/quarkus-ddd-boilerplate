package com.dolap.quarkus.ddd.domain.member.service

import com.dolap.quarkus.ddd.domain.member.entity.Member
import com.dolap.quarkus.ddd.domain.member.vo.PhoneNumber
import com.dolap.quarkus.ddd.domain.member.exception.MemberNotFoundException
import com.dolap.quarkus.ddd.domain.member.repository.MemberRepository
import spock.lang.Specification

class GetMemberServiceSpec extends Specification {

    private GetMemberService getMemberService
    private MemberRepository memberRepository

    def "setup"() {
        memberRepository = Mock(MemberRepository)
        getMemberService = new GetMemberService(memberRepository)
    }

    def "should throw MemberNotFoundException when member does not exists with given id in the repository"() {
        given:
        def memberId = 1L

        when:
        getMemberService.getById(memberId)

        then:
        1 * memberRepository.findMemberById(memberId) >> Optional.empty()
        0 * _
        thrown MemberNotFoundException
    }


    def "should return member when member with given id exists in the repository"() {
        given:
        def memberId = 1L

        def expectedMember = new Member()
        expectedMember.id = memberId
        expectedMember.username = "username"
        expectedMember.password = "password"
        expectedMember.phoneNumber = PhoneNumber.of("countryCode", "number")

        when:
        Member member = getMemberService.getById(memberId)

        then:
        1 * memberRepository.findMemberById(memberId) >> Optional.of(expectedMember)
        0 * _

        member == expectedMember
    }

    def "should return all members successfully"() {
        given:
        def member1 = new Member()
        member1.id = 1l
        member1.username = "username1"
        member1.password = "password1"
        member1.phoneNumber = PhoneNumber.of("countryCode1", "number1")

        def member2 = new Member()
        member2.id = 2L
        member2.username = "username2"
        member2.password = "password2"
        member2.phoneNumber = PhoneNumber.of("countryCode2", "number2")

        when:
        List<Member> members = getMemberService.getAll()

        then:
        1 * memberRepository.findAllMembers() >> [member1, member2]
        0 * _

        members[0] == member1
        members[1] == member2
    }

}
