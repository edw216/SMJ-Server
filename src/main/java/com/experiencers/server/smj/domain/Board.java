package com.experiencers.server.smj.domain;

import com.experiencers.server.smj.enumerate.BoardType;
import com.sun.istack.NotNull;
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
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String writer;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50,nullable = false)
    private BoardType type;

    @NotNull
    @Column(length = 255,nullable = false)
    private String title;

    @NotNull
    @Column(length = 10000,nullable = false)
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Lob
    private String imageOne;

    @Lob
    private String imageTwo;

    @Lob
    private String imageThree;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id",nullable = false)
    private Member member;

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
        private String imageOne;
        private String imageTwo;
        private String imageThree;

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
        public Builder imageOne(String imageOne){
            this.imageOne = imageOne;
            return this;
        }
        public Builder imageTwo(String imageTwo){
            this.imageTwo = imageTwo;
            return this;
        }
        public Builder imageThree(String imageThree){
            this.imageThree = imageThree;
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
        this.imageOne = builder.imageOne;
        this.imageTwo = builder.imageTwo;
        this.imageThree = builder.imageThree;
    }
    public Board() {}

    public static Builder builder(){
        return new Builder();
    }
}
