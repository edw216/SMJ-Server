package com.experiencers.server.smj.domain;

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
    @Column(name = "message_id")
    private Long message_id;
    @Column(nullable = false, length = 255)
    private String content;
    @Column(nullable = false, length = 50)
    private String sender;
    @Column(nullable = false, length = 50)
    private String receiver;
    @CreationTimestamp
    private LocalDateTime date;

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
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
                "message_id=" + message_id +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}