package com.experiencers.server.smj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long commentId;
    @ApiModelProperty(example = "내용")
    @Column(nullable = false, length = 1000)
    private String content;
    @JsonIgnore
    @Column(nullable = false, length = 50)
    private String user;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id",nullable = false)
    private Board board;


    @Override
    public String toString() {
        return "Comment{"+
                "comment_id="+commentId+
                ", content='" +content+'\''+
                ", user='"+user+'\''+
                ", createdAt='"+createdAt+'\''+
                '}';

    }

}