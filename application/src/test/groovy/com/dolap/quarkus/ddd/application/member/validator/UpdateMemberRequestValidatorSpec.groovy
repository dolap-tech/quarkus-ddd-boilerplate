package com.dolap.quarkus.ddd.application.member.validator

import com.dolap.quarkus.ddd.application.base.exception.ValidationException
import com.dolap.quarkus.ddd.application.member.message.request.UpdateMemberRequest
import spock.lang.Specification
import spock.lang.Unroll

class UpdateMemberRequestValidatorSpec extends Specification {

    private UpdateMemberRequestValidator updateMemberRequestValidator

    def "setup"() {
        updateMemberRequestValidator = new UpdateMemberRequestValidator()
    }

    def "should not throw exception when request is valid"() {
        given:
        def request = new UpdateMemberRequest()
        request.username = "a"
        request.id = 1L
        request.phoneCountryCode = "123"
        request.phoneNumber = "123456789012345"

        when:
        updateMemberRequestValidator.validate(request)

        then:
        noExceptionThrown()
    }

    @Unroll
    def "should throw exception with message #expectedErrorMessageKey when request has username[#username] id[#id] countryCode[#countryCode] number[#number]"() {
        given:
        def request = new UpdateMemberRequest()
        request.username = username
        request.id = id
        request.phoneCountryCode = countryCode
        request.phoneNumber = number

        when:
        updateMemberRequestValidator.validate(request)

        then:
        def e = thrown(ValidationException)
        e.message == expectedErrorMessageKey

        where:
        username   | id   | countryCode | number                 | expectedErrorMessageKey
        ""         | 1L   | "90"        | "5554443322"           | "error.validation.username.not.blank"
        "username" | null | "90"        | "5554443322"           | "error.validation.id.not.null"
        "username" | 1L   | ""          | "5554443322"           | "error.validation.phone.country.code.not.blank"
        "username" | 1L   | "90"        | ""                     | "error.validation.phone.number.not.blank"
        "username" | 1L   | "9999"      | "5554443322"           | "error.validation.phone.country.code.max.length"
        "username" | 1L   | "90"        | "1234567"              | "error.validation.phone.number.min.length"
        "username" | 1L   | "90"        | "1"                    | "error.validation.phone.number.min.length"
        "username" | 1L   | "90"        | "1234567890123456"     | "error.validation.phone.number.max.length"
        "username" | 1L   | "90"        | "12345678901234567890" | "error.validation.phone.number.max.length"

    }
}
