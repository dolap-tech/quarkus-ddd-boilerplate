package com.dolap.quarkus.ddd.domain.member.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "number")
    private String number;

    private PhoneNumber(String countryCode, String number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public PhoneNumber() {
    }

    public static PhoneNumber of(String countryCode, String number) {
        return new PhoneNumber(countryCode, number);
    }

    public String getFullPhoneNumber() {
        return this.countryCode.concat(this.number);
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }
}
