package com.experiencers.server.smj.service;

import com.experiencers.server.smj.configuration.JwtAuthTokenProvider;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private JwtAuthTokenProvider jwtAuthTokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    //등록이 안되있는 멤버는 등록을 해줘야 함.
    //이메일과 닉네임 필수
    public String createToken(String email,String nickname){

        Optional findEmail = memberRepository.findByEmail(email);

        if(findEmail == Optional.empty()){
            System.out.println("맴버 생성");
            Member member = new Member();
            member.setEmail(email);
            member.setNickname(nickname);
            memberRepository.save(member);
        }

        String token =jwtAuthTokenProvider.createJwtToken(email);

        return token;
    }
}
