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
    public String createToken(@RequestHeader(value = "email")String email,@RequestHeader(value = "nickname")String nickname){

        return authService.createToken(email,nickname);

    }
    /*@GetMapping("/auth/get")
    public String getUserToken(@RequestBody String token){
        return authService.getUserToken(token);
    }*/
}
