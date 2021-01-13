package com.dolap.quarkus.ddd.application.member.message.dto;

public class MemberDTO {

    private final Long id;
    private final String username;
    private final String phoneNumber;

    private MemberDTO(Long id, String username, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public static MemberDTO create(Long id, String username, String phoneNumber) {
        return new MemberDTO(id, username, phoneNumber);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
