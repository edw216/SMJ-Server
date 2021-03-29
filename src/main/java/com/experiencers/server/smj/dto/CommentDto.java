package com.experiencers.server.smj.dto;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

    @Setter
    @Getter
    public static class CommentDtoRequest{

        @ApiModelProperty(position = 1,notes = "댓글 내용(최대 1000자)")
        private String content;

        public Comment toEntity(Board board, Member member){
            return Comment.builder()
                    .content(this.content)
                    .board(board)
                    .member(member)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class CommentDtoResponse{
        @ApiModelProperty(position = 1,notes = "댓글 구분아이디")
        private Long id;

        @ApiModelProperty(position = 2,notes = "댓글 내용(최대 1000자)")
        private String content;

        @ApiModelProperty(position = 3,notes = "작성자")
        private MemberDto.MemberDtoResponse member;

        @ApiModelProperty(position = 4)
        private LocalDateTime createdAt;

        public static CommentDtoResponse of(Comment comment){
            ModelMapper modelMapper = new ModelMapper();
            CommentDtoResponse dto = modelMapper.map(comment,CommentDtoResponse.class);


            return dto;
        }
        public static List<CommentDtoResponse> of(List<Comment> commentList){

            return commentList.stream()
                    .map(CommentDtoResponse::of)
                    .collect(Collectors.toList());
        }
    }
}
