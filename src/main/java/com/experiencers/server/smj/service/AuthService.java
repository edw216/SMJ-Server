package com.experiencers.server.smj.service;

import com.experiencers.server.smj.configuration.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public String createToken(String userPk){
        String token =jwtTokenProvider.createToken(userPk);

        return token;
    }
}
