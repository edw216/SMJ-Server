package com.experiencers.server.smj.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {
<<<<<<< HEAD:src/main/java/com/experiencers/server/smj/domain/Comment.java
=======

>>>>>>> dongwoo:smj/src/main/java/com/experiencers/server/smj/comment/Comment.java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false, length = 50)
    private String user;
    @CreationTimestamp
<<<<<<< HEAD:src/main/java/com/experiencers/server/smj/domain/Comment.java
    private LocalDateTime createdAt;
=======

    private LocalDateTime createdDate;
>>>>>>> dongwoo:smj/src/main/java/com/experiencers/server/smj/comment/Comment.java

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;

        if(!board.getComments().contains(this)){
            board.getComments().add(this);
        }
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
<<<<<<< HEAD:src/main/java/com/experiencers/server/smj/domain/Comment.java
=======
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
>>>>>>> dongwoo:smj/src/main/java/com/experiencers/server/smj/comment/Comment.java
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

<<<<<<< HEAD:src/main/java/com/experiencers/server/smj/domain/Comment.java
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

=======
>>>>>>> dongwoo:smj/src/main/java/com/experiencers/server/smj/comment/Comment.java
    @Override
    public String toString() {
        return "Comment{"+
                "comment_id="+commentId+
                ", content='" +content+'\''+
                ", user='"+user+'\''+
<<<<<<< HEAD:src/main/java/com/experiencers/server/smj/domain/Comment.java
                ", createdAt='"+createdAt+'\''+
=======
                ", created_date='"+createdDate+'\''+
>>>>>>> dongwoo:smj/src/main/java/com/experiencers/server/smj/comment/Comment.java
                '}';

    }

}
