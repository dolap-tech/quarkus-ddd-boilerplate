package com.dolap.quarkus.ddd.infrastructure.rest;

import com.dolap.quarkus.ddd.application.member.message.request.CreateMemberRequest;
import com.dolap.quarkus.ddd.application.member.message.request.UpdateMemberRequest;
import com.dolap.quarkus.ddd.domain.member.entity.Member;
import com.dolap.quarkus.ddd.domain.member.repository.MemberRepository;
import com.dolap.quarkus.ddd.domain.member.vo.PhoneNumber;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class MemberRestControllerIntegrationTest {

    @Inject
    EntityManager entityManager;

    @Inject
    MemberRepository memberRepository;

    private final List<Member> persistedMembers = new ArrayList<>();

    @BeforeEach
    public void beforeEach() {
        persistedMembers.add(createMember("username1", "password1", "90", "5554443322"));
        persistedMembers.add(createMember("username2", "password2", "1", "123456789"));
    }

    @AfterEach
    public void afterEach() {
        entityManager.createNativeQuery("truncate table member").executeUpdate();
        persistedMembers.clear();
    }

    @Test
    public void shouldGetAllMembers() {
        given()
                .when().get("/member")
                .then()
                .statusCode(200)
                .body("members", hasSize(2))
                .body("members[0].id", is(persistedMembers.get(0).getId().intValue()))
                .body("members[0].username", is(persistedMembers.get(0).getUsername()))
                .body("members[0].phoneNumber", is(persistedMembers.get(0).getPhoneNumber().getFullPhoneNumber()))
                .body("members[1].id", is(persistedMembers.get(1).getId().intValue()))
                .body("members[1].username", is(persistedMembers.get(1).getUsername()))
                .body("members[1].phoneNumber", is(persistedMembers.get(1).getPhoneNumber().getFullPhoneNumber()));
    }

    @Test
    public void shouldGetMemberById() {
        Member member = persistedMembers.get(0);
        given()
                .when().get("/member/" + member.getId())
                .then()
                .statusCode(200)
                .body("member.id", is(member.getId().intValue()))
                .body("member.username", is(member.getUsername()))
                .body("member.phoneNumber", is(member.getPhoneNumber().getFullPhoneNumber()));
    }

    @Test
    public void shouldGetErrorResponseWhenRequestContainsNonExistingMemberId() {
        given()
                .when().get("/member/" + Long.MAX_VALUE)
                .then()
                .statusCode(400)
                .body("status", is("ERROR"))
                .body("errorMessage", is("Member was not found"));
    }

    @Test
    public void shouldDeleteMemberById() {
        Member member = persistedMembers.get(0);
        given()
                .when().delete("/member/" + member.getId())
                .then()
                .statusCode(200);

        assertFalse(memberRepository.findMemberById(member.getId()).isPresent());
    }

    @Test
    public void shouldGetErrorResponseWhenDeleteRequestContainsNonExistingMemberId() {
        given()
                .when().delete("/member/" + Long.MAX_VALUE)
                .then()
                .statusCode(400)
                .body("status", is("ERROR"))
                .body("errorMessage", is("Member was not found"));
    }

    @Test
    public void shouldCreateMember() {
        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
        createMemberRequest.setUsername("newUsername");
        createMemberRequest.setPassword("newPassword");
        createMemberRequest.setPhoneCountryCode("90");
        createMemberRequest.setPhoneNumber("123456789");

        given()
                .contentType("application/json")
                .body(createMemberRequest)
                .when().post("/member")
                .then()
                .statusCode(200)
                .body("member", hasKey("id"))
                .body("member.username", is(createMemberRequest.getUsername()))
                .body("member.phoneNumber", is(createMemberRequest.getPhoneCountryCode() + createMemberRequest.getPhoneNumber()));

        assertEquals(memberRepository.findAllMembers().size(), 3);
    }

    @Test
    public void shouldUpdateMember() {
        Member member = persistedMembers.get(0);

        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest();
        updateMemberRequest.setId(member.getId());
        updateMemberRequest.setUsername("updatedUsername");
        updateMemberRequest.setPhoneCountryCode("10");
        updateMemberRequest.setPhoneNumber("987654321");

        given()
                .contentType("application/json")
                .body(updateMemberRequest)
                .when().put("/member")
                .then()
                .statusCode(200)
                .body("member.id", is(member.getId().intValue()))
                .body("member.username", is(updateMemberRequest.getUsername()))
                .body("member.phoneNumber", is(updateMemberRequest.getPhoneCountryCode() + updateMemberRequest.getPhoneNumber()));
    }

    private Member createMember(String username, String password, String countryCode, String number) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setPhoneNumber(PhoneNumber.of(countryCode, number));
        memberRepository.save(member);
        return member;
    }
}
