package com.experiencers.server.smj.domain;

import com.experiencers.server.smj.enumerate.BoardType;
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
<<<<<<< HEAD:src/main/java/com/experiencers/server/smj/domain/Board.java
    private Long id;
=======
    private Long boardId;
>>>>>>> dongwoo:smj/src/main/java/com/experiencers/server/smj/board/Board.java
    @Column(nullable = false, length = 50)
    private BoardType type;
    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, length = 10000)
    private String content;
    @CreationTimestamp
<<<<<<< HEAD:src/main/java/com/experiencers/server/smj/domain/Board.java
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name ="member_id")
    private Member member;

=======
    private LocalDateTime createdDate;
    /*@Column(name = "category")
    private Category category; // FK - Category
    @Column(name = "user")
    private User user; // FK - User
    @Column(name = "comment")
    private Comment comment; // FK - Comment */
>>>>>>> dongwoo:smj/src/main/java/com/experiencers/server/smj/board/Board.java
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

<<<<<<< HEAD:src/main/java/com/experiencers/server/smj/domain/Board.java
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;

        if (!category.getBoards().contains(this)) {
            category.getBoards().add(this);
        }
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;

        if(!member.getBoards().contains(this)){
            member.getBoards().add(this);
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
=======
    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
>>>>>>> dongwoo:smj/src/main/java/com/experiencers/server/smj/board/Board.java
    }

    public BoardType getType() {
        return type;
    }

    public void setType(BoardType type) {
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

<<<<<<< HEAD:src/main/java/com/experiencers/server/smj/domain/Board.java
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
=======
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
>>>>>>> dongwoo:smj/src/main/java/com/experiencers/server/smj/board/Board.java
    }

    @Override
    public String toString() {
        return "Board{"+
<<<<<<< HEAD:src/main/java/com/experiencers/server/smj/domain/Board.java
                "id="+id+
                ", type='" +type+'\''+
                ", title='"+title+'\''+
                ", content='"+content+'\''+
                ", createdAt='"+createdAt+'\''+
=======
                "board_id="+boardId+
                ", type='" +type+'\''+
                ", title='"+title+'\''+
                ", content='"+content+'\''+
                ", created_date='"+createdDate+'\''+
>>>>>>> dongwoo:smj/src/main/java/com/experiencers/server/smj/board/Board.java
                '}';

    }
}
