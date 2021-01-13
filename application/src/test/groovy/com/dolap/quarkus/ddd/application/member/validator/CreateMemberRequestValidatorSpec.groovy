package com.dolap.quarkus.ddd.application.member.validator

import com.dolap.quarkus.ddd.application.base.exception.ValidationException
import com.dolap.quarkus.ddd.application.member.message.request.CreateMemberRequest
import spock.lang.Specification
import spock.lang.Unroll

class CreateMemberRequestValidatorSpec extends Specification {

    private CreateMemberRequestValidator createMemberRequestValidator

    def "setup"() {
        createMemberRequestValidator = new CreateMemberRequestValidator()
    }

    def "should not throw exception when request is valid"() {
        given:
        def request = new CreateMemberRequest()
        request.username = "a"
        request.password = "b"
        request.phoneCountryCode = "123"
        request.phoneNumber = "123456789012345"

        when:
        createMemberRequestValidator.validate(request)

        then:
        noExceptionThrown()
    }

    @Unroll
    def "should throw exception with message #expectedErrorMessageKey when request has username[#username] password[#password] countryCode[#countryCode] number[#number]"() {
        given:
        def request = new CreateMemberRequest()
        request.username = username
        request.password = password
        request.phoneCountryCode = countryCode
        request.phoneNumber = number

        when:
        createMemberRequestValidator.validate(request)

        then:
        def e = thrown(ValidationException)
        e.message == expectedErrorMessageKey

        where:
        username   | password   | countryCode | number                 | expectedErrorMessageKey
        ""         | "password" | "90"        | "5554443322"           | "error.validation.username.not.blank"
        "username" | ""         | "90"        | "5554443322"           | "error.validation.password.not.blank"
        "username" | "password" | ""          | "5554443322"           | "error.validation.phone.country.code.not.blank"
        "username" | "password" | "90"        | ""                     | "error.validation.phone.number.not.blank"
        "username" | "password" | "9999"      | "5554443322"           | "error.validation.phone.country.code.max.length"
        "username" | "password" | "90"        | "1234567"              | "error.validation.phone.number.min.length"
        "username" | "password" | "90"        | "1"                    | "error.validation.phone.number.min.length"
        "username" | "password" | "90"        | "1234567890123456"     | "error.validation.phone.number.max.length"
        "username" | "password" | "90"        | "12345678901234567890" | "error.validation.phone.number.max.length"

    }
}
