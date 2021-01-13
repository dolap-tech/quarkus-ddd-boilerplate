package com.dolap.quarkus.ddd.domain.member.service;

import com.dolap.quarkus.ddd.domain.member.exception.MemberNotFoundException;
import com.dolap.quarkus.ddd.domain.member.repository.MemberRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeleteMemberService {

    private final MemberRepository memberRepository;

    @Inject
    public DeleteMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void deleteMember(Long id) {
        boolean isDeleted = memberRepository.deleteMemberById(id);

        if (!isDeleted) {
            throw new MemberNotFoundException();
        }
    }

}
