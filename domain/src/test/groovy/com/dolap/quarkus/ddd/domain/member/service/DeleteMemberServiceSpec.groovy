package com.dolap.quarkus.ddd.domain.member.service


import com.dolap.quarkus.ddd.domain.member.exception.MemberNotFoundException
import com.dolap.quarkus.ddd.domain.member.repository.MemberRepository
import spock.lang.Specification

class DeleteMemberServiceSpec extends Specification {

    private DeleteMemberService deleteMemberService
    private MemberRepository memberRepository

    def "setup"() {
        memberRepository = Mock(MemberRepository)
        deleteMemberService = new DeleteMemberService(memberRepository)
    }

    def "should throw MemberNotFoundException when member with given id does not exists in the repository"() {
        given:
        def memberId = 1L

        when:
        deleteMemberService.deleteMember(memberId)

        then:
        1 * memberRepository.deleteMemberById(memberId) >> false
        0 * _
        thrown MemberNotFoundException
    }

    def "should delete member successfully"() {
        given:
        def memberId = 1L

        when:
        deleteMemberService.deleteMember(memberId)

        then:
        1 * memberRepository.deleteMemberById(memberId) >> true
        0 * _
    }

}
