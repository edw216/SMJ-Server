package com.experiencers.server.smj.api;

import com.experiencers.server.smj.service.KakaoAndAuthService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/auth")
public class KakaoAndAuthApiController {

    @Autowired
    private KakaoAndAuthService kakaoService;

    @ApiOperation(value = "로그인권한",notes = "헤더에 Kakao Access Token을 넣으면 성공시 JWT토큰을 반환합니다.")
    @GetMapping(value = "/token")
    public String kakaoProfileAndCreateToken(@RequestHeader("Authorization")String token) {

        return kakaoService.getProfileAndCreateToken(token);
    }
}
