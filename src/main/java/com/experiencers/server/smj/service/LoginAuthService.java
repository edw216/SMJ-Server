package com.experiencers.server.smj.service;

import com.experiencers.server.smj.configuration.JwtAuthTokenProvider;
import com.experiencers.server.smj.dto.KakaoProfileDto;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.domain.Setting;
import com.experiencers.server.smj.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class LoginAuthService {
    @Autowired
    private JwtAuthTokenProvider jwtAuthTokenProvider;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    public String getProfileAndCreateToken(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        //HttpHeader에 카카오 서버에 맞는 형식의 데이터를 담음
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+accessToken);
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(headers);

        System.out.println(request.getHeaders());
        //Http데이터를 카카오 서버에 전송하여 Response 개체에 받아오기
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfileDto profile = null;

        //받아온 Response Body의 사용자 데이터를 저장하기
        try{
            profile = objectMapper.readValue(response.getBody(), KakaoProfileDto.class);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        //받아온 사용자의 데이터가 이미 저장이 되있나 판별하고 저장할지 결정
        Optional<Member> findEmail = memberRepository.findByEmail(profile.kakao_account.email);

        Member member;
        if(!findEmail.isPresent()){
            member = Member.builder()
                    .email(profile.kakao_account.email)
                    .nickname(profile.kakao_account.profile.nickname)
                    .image(profile.kakao_account.profile.profile_image_url)
                    .setting(new Setting())
                    .build();
            memberRepository.save(member);
            //memberService.saveMember(member);

        }else{
            member = findEmail.get();
        }

        System.out.println(profile.getId());
        System.out.println(profile.kakao_account.profile.nickname);
        System.out.println(profile.kakao_account.email);
        System.out.println(profile.properties.getProfile_image());
        System.out.println(profile.kakao_account.profile.profile_image_url);

        //저장한 member객체의 이메일을 가져와서 jwt토큰을 발급
        String jwtToken =jwtAuthTokenProvider.createJwtToken(member.getEmail());
        System.out.println(jwtToken);

        return jwtToken;
    }
}

