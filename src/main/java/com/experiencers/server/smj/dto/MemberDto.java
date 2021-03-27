package com.experiencers.server.smj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    @ApiModelProperty(position = 1,notes = "멤버 이메일",example = "email@example.com")
    private String email;

    @ApiModelProperty(position = 2,notes = "멤버 닉네임",example = "사용자 닉네임")
    private String nickname;

    @ApiModelProperty(position = 3,notes = "멤버 이미지")
    private String image;

}
