package com.experiencers.server.smj.domain;

import com.experiencers.server.smj.enumerate.BoardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
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
    @ApiModelProperty(example = "LIVE or TRADE")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private BoardType type;
    @ApiModelProperty(example = "제목")
    @Column(nullable = false, length = 255)
    private String title;
    @ApiModelProperty(example = "내용")
    @Column(nullable = false, length = 10000)
    private String content;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id",nullable = false)
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
                '}';

    }
}
