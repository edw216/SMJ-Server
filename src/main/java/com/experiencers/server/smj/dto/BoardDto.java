package com.experiencers.server.smj.dto;


import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.enumerate.BoardType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

    @Getter
    @Setter
    public static class BoardDtoRequest {

        @ApiModelProperty(position = 1,notes = "카테고리 구분아이디(카테고리 참고)")
        private Long categoryId;

        @ApiModelProperty(position = 2,notes = "게시글 유형",example = "LIVE, TRADE")
        @Enumerated(EnumType.STRING)
        private BoardType type;

        @ApiModelProperty(position = 3,notes = "게시글 제목(최대 255자)")
        private String title;

        @ApiModelProperty(position = 4,notes = "게시글 내용(최대 10000자)")
        private String content;

        @ApiModelProperty(position = 5,notes = "게시글 사진1")
        private String imageOne;

        @ApiModelProperty(position = 6,notes = "게시글 사진2")
        private String imageTwo;

        @ApiModelProperty(position = 7,notes = "게시글 사진3")
        private String imageThree;

        public Board toEntity(Category category, String writer, Member member){
            return Board.builder()
                    .category(category)
                    .type(this.type)
                    .title(this.title)
                    .content(this.content)
                    .imageOne(this.imageOne)
                    .imageTwo(this.imageTwo)
                    .imageThree(this.imageThree)
                    .writer(writer)
                    .member(member)
                    .build();
        }

    }

    @Getter
    @Setter
    public static class BoardDtoResponse {
        @ApiModelProperty(position = 1,notes = "게시글 구분아이디")
        private Long id;

        @ApiModelProperty(position = 2,notes = "게시글 카테고리")
        private CategoryDto.CategoryDtoResponse category;

        @ApiModelProperty(position = 3,notes = "게시글 작성자")
        private String writer;

        @ApiModelProperty(position = 4,notes = "게시글 유형")
        private BoardType type;

        @ApiModelProperty(position = 5,notes = "게시글 제목(최대 255자)")
        private String title;

        @ApiModelProperty(position = 6,notes = "게시글 내용(최대 10000자)")
        private String content;

        @ApiModelProperty(position = 7,notes = "게시글 사진1")
        private String imageOne;

        @ApiModelProperty(position = 8,notes = "게시글 사진2")
        private String imageTwo;

        @ApiModelProperty(position = 9,notes = "게시글 사진3")
        private String imageThree;

        @ApiModelProperty(position = 10)
        private LocalDateTime createdAt;

        public static BoardDtoResponse of(Board board){
            ModelMapper modelMapper = new ModelMapper();
            BoardDtoResponse dto = modelMapper.map(board, BoardDtoResponse.class);

            return dto;
        }

        public static List<BoardDtoResponse> of(List<Board> boardList){

            return boardList.stream()
                    .map(BoardDtoResponse::of)
                    .collect(Collectors.toList());
        }

    }

}
