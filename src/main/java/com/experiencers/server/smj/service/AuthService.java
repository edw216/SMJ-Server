package com.experiencers.server.smj.service;

import com.experiencers.server.smj.configuration.JwtAuthTokenProvider;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    @Autowired
    private MemberRepository memberRepository;

    //등록이 안되있는 멤버는 등록을 해줘야 함.
    public String createToken(String userPk){
        Member member = new Member();
        member.setEmail(userPk);
        member.setNickname("동우");

        memberRepository.save(member);
        String token =jwtAuthTokenProvider.createJwtToken(userPk);

        return token;
    }
    /*public String getUserToken(String token){
        String user = jwtTokenProvider.getUserPk(token);

        return user;
    }*/
}
