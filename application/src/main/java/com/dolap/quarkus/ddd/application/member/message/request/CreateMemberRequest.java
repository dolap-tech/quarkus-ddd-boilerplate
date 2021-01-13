package com.dolap.quarkus.ddd.application.member.message.request;

public class CreateMemberRequest {

    private String username;
    private String password;
    private String phoneCountryCode;
    private String phoneNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CreateMemberRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneCountryCode='" + phoneCountryCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
