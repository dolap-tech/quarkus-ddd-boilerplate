package com.dolap.quarkus.ddd.domain.member.entity;

import com.dolap.quarkus.ddd.domain.member.vo.PhoneNumber;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Member {

    @Id
    @SequenceGenerator(name = "memberSequence", sequenceName = "seq_member")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberSequence")
    private Long id;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    @Embedded
    public PhoneNumber phoneNumber;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
