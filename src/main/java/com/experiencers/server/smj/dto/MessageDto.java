package com.experiencers.server.smj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageDto {
    @ApiModelProperty(position = 1,notes = "쪽지 내용(최대 255자)")
    private String content;

    @ApiModelProperty(position = 2,notes = "쪽지 받는사람(이메일)",example = "example@example.com")
    private String receiver;
}
