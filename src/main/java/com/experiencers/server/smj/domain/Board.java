package com.experiencers.server.smj.domain;

import com.experiencers.server.smj.enumerate.BoardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    @ApiModelProperty(position = 1,notes = "게시글 아이디")
    private Long id;

    @NotNull
    @ApiModelProperty(position = 2,notes = "작성자")
    private String writer;

    @NotNull
    @ApiModelProperty(position = 3,notes = "게시글 유형",example = "LIVE, TRADE")
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private BoardType type;

    @NotNull
    @ApiModelProperty(position = 4,notes = "게시글 제목(최대 255자)")
    @Column(length = 255)
    private String title;

    @NotNull
    @ApiModelProperty(position = 5,notes = "게시글 내용(최대 10000자)")
    @Column(length = 10000)
    private String content;

    @ApiModelProperty(position = 7)
    @CreationTimestamp
    private LocalDateTime createdAt;


    @ApiModelProperty(position = 6,notes = "게시글 카테고리(카테고리 참고)")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;


    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();


    @Override
    public String toString() {
        return "Board{"+
                "id="+id+
                ", type='" +type+'\''+
                ", title='"+title+'\''+
                ", content='"+content+'\''+
                ", createdAt='"+createdAt+'\''+
                ", category='"+category+'\''+
                '}';

    }

    public static class Builder{
        private String writer;
        private BoardType type;
        private String title;
        private String content;
        private Category category;
        private Member member;

        public Builder writer(String writer){
            this.writer = writer;
            return this;
        }
        public Builder type(BoardType type){
            this.type = type;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Builder category(Category category){
            this.category = category;
            return this;
        }
        public Builder member(Member member){
            this.member = member;
            return this;
        }
        public Board build(){
            return new Board(this);
        }
    }


    private Board(Builder builder){
        this.writer = builder.writer;
        this.type = builder.type;
        this.title = builder.title;
        this.content = builder.content;
        this.category = builder.category;
        this.member = builder.member;
    }
    public Board() {}

    public static Builder builder(){
        return new Builder();
    }
}
