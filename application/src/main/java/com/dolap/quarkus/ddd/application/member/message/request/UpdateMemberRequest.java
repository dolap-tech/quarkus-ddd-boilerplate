package com.dolap.quarkus.ddd.application.member.message.request;

public class UpdateMemberRequest {

    private Long id;
    private String username;
    private String phoneCountryCode;
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "UpdateMemberRequest{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phoneCountryCode='" + phoneCountryCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
