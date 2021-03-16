package com.experiencers.server.smj.api;

import com.experiencers.server.smj.service.LoginAuthService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = "Login", description = "로그인")
@Slf4j
@RestController
@RequestMapping("/api/auth/token")
public class LoginAuthApiController {

    @Autowired
    private LoginAuthService loginAuthService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiOperation(value = "로그인권한 발급",notes = "헤더에 Kakao Access Token을 넣으면 성공시 JWT토큰을 반환합니다.")
    @GetMapping("")
    public ResponseEntity<?> loginAndCreateToken(@RequestHeader("Authorization")String token) {
        String jwtToken = loginAuthService.getProfileAndCreateToken(token);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
}
