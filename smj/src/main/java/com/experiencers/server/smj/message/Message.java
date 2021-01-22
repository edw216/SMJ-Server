package com.experiencers.server.smj.message;

import com.experiencers.server.smj.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {
    public Message(){
        final LocalDateTime t = LocalDateTime.now();
        date = t;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private Long messageId;
    @Column(nullable = false, length = 255)
    private String content;
    @Column(nullable = false, length = 50)
    private String sender;
    @Column(nullable = false, length = 50)
    private String receiver;
    @CreationTimestamp
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user = user;

        if(!user.getMessages().contains(this)){
            user.getMessages().add(this);
        }
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getDate(){ return date;}

    public void setDate(LocalDateTime date){ this.date=date; }
    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}