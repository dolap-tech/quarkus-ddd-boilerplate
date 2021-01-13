package com.dolap.quarkus.ddd.application.member.converter

import com.dolap.quarkus.ddd.application.member.message.dto.MemberDTO
import com.dolap.quarkus.ddd.domain.member.entity.Member
import com.dolap.quarkus.ddd.domain.member.vo.PhoneNumber
import spock.lang.Specification

class MemberToMemberDTOConverterSpec extends Specification {

    private MemberToMemberDTOConverter memberToMemberDTOConverter

    def "setup"() {
        memberToMemberDTOConverter = new MemberToMemberDTOConverter()
    }

    def "should convert given Member to MemberDTO successfully"() {
        given:
        def member = new Member()
        member.id = 1L
        member.username = "username"
        member.phoneNumber = PhoneNumber.of("countryCode", "number")

        when:
        MemberDTO memberDTO = memberToMemberDTOConverter.convert(member)

        then:
        memberDTO.id == member.id
        memberDTO.username == member.username
        memberDTO.phoneNumber == member.phoneNumber.getFullPhoneNumber()
    }
}
