package com.experiencers.server.smj.api;

import com.experiencers.server.smj.service.KakaoAndAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/auth")
public class KakaoAndAuthApiController {

    @Autowired
    private KakaoAndAuthService kakaoService;

    @GetMapping(value = "/token")
    public String kakaoProfileAndCreateToken(@RequestHeader("Authorization")String token) {

        return kakaoService.getProfileAndCreateToken(token);
    }
}
