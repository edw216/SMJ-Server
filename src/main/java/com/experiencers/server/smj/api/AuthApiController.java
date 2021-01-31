package com.experiencers.server.smj.api;

import com.experiencers.server.smj.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthApiController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/token")
    public String createToken(){
        return authService.createToken("ehddn216@naver.com");

    }
}
