package com.dolap.quarkus.ddd.domain.member.service;

import com.dolap.quarkus.ddd.domain.member.entity.Member;
import com.dolap.quarkus.ddd.domain.member.exception.MemberNotFoundException;
import com.dolap.quarkus.ddd.domain.member.repository.MemberRepository;
import com.dolap.quarkus.ddd.domain.member.vo.PhoneNumber;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class UpdateMemberService {

    private final MemberRepository memberRepository;

    @Inject
    public UpdateMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member update(Long id, String username, String countryCode, String number) {
        Member member = memberRepository.findMemberById(id)
                .orElseThrow(MemberNotFoundException::new);

        member.setId(id);
        member.setUsername(username);
        member.setPhoneNumber(PhoneNumber.of(countryCode, number));

        memberRepository.save(member);
        return member;
    }

}
