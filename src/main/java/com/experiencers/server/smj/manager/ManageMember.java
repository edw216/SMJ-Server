package com.experiencers.server.smj.manager;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class ManageMember {

    @Autowired
    private MemberRepository memberRepository;

    public Member getManageMember(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberRepository.findByEmail(user.getUsername()).get();

        return member;
    }

    public String getManageMembername(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return user.getUsername();
    }
}
