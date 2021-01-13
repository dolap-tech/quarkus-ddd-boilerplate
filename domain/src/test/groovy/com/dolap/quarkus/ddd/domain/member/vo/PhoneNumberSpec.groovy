package com.dolap.quarkus.ddd.domain.member.vo

import spock.lang.Specification

class PhoneNumberSpec extends Specification {

    def "should return full phone number correctly"() {
        given:
        def phoneNumber = PhoneNumber.of("90", "5554443322")

        when:
        def fullPhoneNumber = phoneNumber.getFullPhoneNumber()

        then:
        fullPhoneNumber == phoneNumber.countryCode + phoneNumber.number
    }
}
