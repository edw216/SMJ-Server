package com.experiencers.server.smj.comment;

import com.experiencers.server.smj.board.Board;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {
    public Comment(){
        final LocalDateTime now = LocalDateTime.now();
        created_date = now;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long comment_id;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false, length = 50)
    private String user; //FK - User
    @CreationTimestamp
    private LocalDateTime created_date;

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

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
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

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Comment{"+
                "comment_id="+comment_id+
                ", content='" +content+'\''+
                ", user='"+user+'\''+
                ", created_date='"+created_date+'\''+
                '}';

    }

}
