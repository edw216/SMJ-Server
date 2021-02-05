package com.experiencers.server.smj.api;

import com.experiencers.server.smj.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthApiController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/token")
    public String createToken(){

        return authService.createToken("ehddn216666@naver.com");

    }
    /*@GetMapping("/auth/get")
    public String getUserToken(@RequestBody String token){
        return authService.getUserToken(token);
    }*/
}
