package com.experiencers.server.smj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    @ApiModelProperty(position = 1,notes = "댓글 내용(최대 1000자)")
    private String content;

}
