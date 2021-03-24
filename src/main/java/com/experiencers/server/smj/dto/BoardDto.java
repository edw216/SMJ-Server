package com.experiencers.server.smj.dto;


import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.enumerate.BoardType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@NoArgsConstructor
public class BoardDto {
    @ApiModelProperty(position = 1,notes = "카테고리 구분 아이디")
    private Long categoryId;

    @ApiModelProperty(position = 2,notes = "게시글 유형",example = "LIVE, TRADE")
    @Enumerated(EnumType.STRING)
    private BoardType type;

    @ApiModelProperty(position = 3,notes = "게시글 제목(최대 255자)")
    private String title;

    @ApiModelProperty(position = 4,notes = "게시글 내용(최대 10000자)")
    private String content;





}
