package com.experiencers.server.smj.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 50)
    private String nickname;
    @Lob
    private String image;
    @CreationTimestamp
    private LocalDateTime createAt;


    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoard(Board board) {
        this.boards.add(board);
        if(board.getMember() != this){
            board.setMember(this);
        }
    }
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }
    public void addMessages(Message message){
        this.messages.add(message);
        if(message.getMember() != this){
            message.setMember(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", image='" + image + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }

}
