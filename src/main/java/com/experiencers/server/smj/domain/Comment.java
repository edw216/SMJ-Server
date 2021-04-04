package com.experiencers.server.smj.domain;

import com.sun.istack.NotNull;
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
    private Long id;

    @NotNull
    @Column(length = 1000,nullable = false)
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id",nullable = false)
    private Board board;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @Override
    public String toString() {
        return "Comment{"+
                "id="+id+
                ", content='" +content+'\''+
                ", createdAt='"+createdAt+'\''+
                '}';

    }

    public static class Builder{
        private String content;
        private Board board;
        private Member member;

        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Builder board(Board board){
            this.board = board;
            return this;
        }
        public Builder member(Member member){
            this.member = member;
            return this;
        }
        public Comment build(){
            return new Comment(this);
        }
    }
    private Comment(Builder builder){
        this.content = builder.content;
        this.board = builder.board;
        this.member = builder.member;
    }

    public Comment(){}

    public static Builder builder(){
        return new Builder();
    }

}