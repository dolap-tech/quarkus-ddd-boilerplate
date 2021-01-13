package com.dolap.quarkus.ddd.domain.member.service

import com.dolap.quarkus.ddd.domain.member.entity.Member
import com.dolap.quarkus.ddd.domain.member.exception.MemberAlreadyExistsException
import com.dolap.quarkus.ddd.domain.member.vo.PhoneNumber
import com.dolap.quarkus.ddd.domain.member.repository.MemberRepository
import groovy.json.JsonOutput
import spock.lang.Specification

class CreateMemberServiceSpec extends Specification {

    private CreateMemberService createMemberService
    private MemberRepository memberRepository

    def "setup"() {
        memberRepository = Mock(MemberRepository)
        createMemberService = new CreateMemberService(memberRepository)
    }

    def "should throw MemberAlreadyExistsException when member with username exists in the repository"() {
        given:
        def username = "existingUsername"
        def password = "password"
        def phoneCountryCode = "countryCode"
        def phoneNumber = "number"

        def existingMember = new Member()

        when:
        createMemberService.create(username, password, phoneCountryCode, phoneNumber)

        then:
        1 * memberRepository.findMemberByUsername(username) >> Optional.of(existingMember)
        0 * _
        thrown MemberAlreadyExistsException
    }

    def "should create member and return successfully"() {
        given:
        def username = "username"
        def password = "password"
        def phoneCountryCode = "countryCode"
        def phoneNumber = "number"

        def expectedMember = new Member()
        expectedMember.username = username
        expectedMember.password = password
        expectedMember.phoneNumber = PhoneNumber.of(phoneCountryCode, phoneNumber)

        when:
        createMemberService.create(username, password, phoneCountryCode, phoneNumber)

        then:
        1 * memberRepository.findMemberByUsername(username) >> Optional.empty()
        1 * memberRepository.save({ JsonOutput.toJson(it) == JsonOutput.toJson(expectedMember) })
        0 * _
    }

}
