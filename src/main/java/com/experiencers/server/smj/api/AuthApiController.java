package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthApiController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/token")
    public String createToken(@RequestBody Member member){
        System.out.println(member.getEmail()+" "+member.getNickname());

        return authService.createToken(member.getEmail(), member.getNickname());
    }
}
