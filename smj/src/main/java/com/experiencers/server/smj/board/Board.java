package com.experiencers.server.smj.board;

import com.experiencers.server.smj.comment.Comment;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
public class Board {
    public Board(){
        final LocalDateTime now = LocalDateTime.now();
        created_date = now;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long board_id;
    @Column(nullable = false, length = 50)
    private String type;
    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, length = 5000)
    private String content;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime created_date;
    /*@Column(name = "category")
    private Category category; // FK - Category
    @Column(name = "user")
    private User user; // FK - User
    @Column(name = "comment")
    private Comment comment; // FK - Comment */
    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comment){
        this.comments.add(comment);
        if(comment.getBoard() != this){
            comment.setBoard(this);
        }
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Board{"+
                "board_id="+board_id+
                ", type='" +type+'\''+
                ", title='"+title+'\''+
                ", content='"+content+'\''+
                ", created_date='"+created_date+'\''+
                '}';

    }
}
