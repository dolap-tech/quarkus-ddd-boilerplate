package com.dolap.quarkus.ddd.application.member.validator;

import com.dolap.quarkus.ddd.application.base.validator.RequestValidator;
import com.dolap.quarkus.ddd.application.member.message.request.UpdateMemberRequest;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateMemberRequestValidator extends RequestValidator {

    public void validate(UpdateMemberRequest request) {
        validateNotNull(request.getId(), "error.validation.id.not.null");
        validateStringNotBlank(request.getUsername(), "error.validation.username.not.blank");
        validateStringNotBlank(request.getPhoneCountryCode(), "error.validation.phone.country.code.not.blank");
        validateStringNotBlank(request.getPhoneNumber(), "error.validation.phone.number.not.blank");
        validateMaxStringLength(request.getPhoneCountryCode(), 3, "error.validation.phone.country.code.max.length");
        validateMinStringLength(request.getPhoneNumber(), 8, "error.validation.phone.number.min.length");
        validateMaxStringLength(request.getPhoneNumber(), 15, "error.validation.phone.number.max.length");
    }

}
