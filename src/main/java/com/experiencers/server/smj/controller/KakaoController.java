package com.experiencers.server.smj.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * 여기는 카카오 토큰 발급 받기위한 테스트 페이지
 * 추후에 삭제 예정
 */
@Controller
@RequestMapping
public class KakaoController {

    @GetMapping("/test")
    public ModelAndView getIndex(){
        ModelAndView response = new ModelAndView("kakao/login");

        return response;
    }

    @GetMapping("auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code){
        //POST 방식으로 key = value 데이터를 요청(카카오서버로)
        RestTemplate rt = new RestTemplate();
        //HttpHeader 에 데이터담기
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 에 데이터담기
        MultiValueMap<String,String> param = new LinkedMultiValueMap<>();
        param.add("grant_type","authorization_code");
        param.add("client_id","7223a57f35e190fcd95cebeca998a35b");
        param.add("redirect_uri","https://smj-server-heroku.herokuapp.com/auth/kakao/callback");
        param.add("code",code);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(param,headers);

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        return "카카오 토큰 요청완료: 토큰요청에 대한 응답"+ response.getBody();
    }
}
