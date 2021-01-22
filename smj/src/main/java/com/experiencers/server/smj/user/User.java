package com.experiencers.server.smj.user;

import com.experiencers.server.smj.message.Message;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 50)
    private String nickname;
    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }
    public void addMessages(Message message){
        this.messages.add(message);
        if(message.getUser() != this){
            message.setUser(this);
        }
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
