package com.dolap.quarkus.ddd.application.base.validator;

import com.dolap.quarkus.ddd.application.base.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;

public abstract class RequestValidator {

    protected void validateNotNull(Object value, String errorMessageKey) {
        if (value == null) {
            throw new ValidationException(errorMessageKey);
        }
    }

    protected void validateStringNotBlank(String stringValue, String errorMessageKey) {
        if (StringUtils.isBlank(stringValue)) {
            throw new ValidationException(errorMessageKey);
        }
    }

    protected void validateMinStringLength(String stringValue, int length, String errorMessageKey) {
        if (StringUtils.length(stringValue) < length) {
            throw new ValidationException(errorMessageKey);
        }
    }

    protected void validateMaxStringLength(String stringValue, int length, String errorMessageKey) {
        if (StringUtils.length(stringValue) > length) {
            throw new ValidationException(errorMessageKey);
        }
    }

}
