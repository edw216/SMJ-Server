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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;
    @Column(nullable = false, length = 50)
    private String type;
    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, length = 5000)
    private String content;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
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

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Board{"+
                "board_id="+boardId+
                ", type='" +type+'\''+
                ", title='"+title+'\''+
                ", content='"+content+'\''+
                ", created_date='"+createdDate+'\''+
                '}';

    }
}
