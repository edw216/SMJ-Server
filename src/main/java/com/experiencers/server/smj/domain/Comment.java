package com.experiencers.server.smj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @ApiModelProperty(position = 1,notes = "댓글 아이디")
    private Long id;

    @NotNull
    @ApiModelProperty(position = 2,notes = "댓글 내용(최대 1000자)")
    @Column(length = 1000)
    private String content;

    @NotNull
    @ApiModelProperty(position = 3,notes = "작성자")
    @Column(length = 50)
    private String user;

    @ApiModelProperty(position = 4)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


    @Override
    public String toString() {
        return "Comment{"+
                "id="+id+
                ", content='" +content+'\''+
                ", user='"+user+'\''+
                ", createdAt='"+createdAt+'\''+
                '}';

    }

    public static class Builder{
        private String content;
        private String user;
        private Board board;

        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Builder user(String user){
            this.user = user;
            return this;
        }
        public Builder board(Board board){
            this.board = board;
            return this;
        }
        public Comment build(){
            return new Comment(this);
        }
    }
    private Comment(Builder builder){
        this.content = builder.content;
        this.user = builder.user;
        this.board = builder.board;
    }

    public Comment(){}

    public static Builder builder(){
        return new Builder();
    }

}