package com.experiencers.server.smj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @NotNull
    @Column(length = 255,nullable = false)
    private String content;

    @NotNull
    @Column(length = 50,nullable = false)
    private String sender;

    @NotNull
    @Column(length = 50,nullable = false)
    private String receiver;

    @CreationTimestamp
    private LocalDateTime createAt;


    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }

    public static class Builder{
        private String content;
        private String sender;
        private String receiver;

        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Builder sender(String sender){
            this.sender = sender;
            return this;
        }
        public Builder receiver(String receiver){
            this.receiver = receiver;
            return this;
        }
        public Message build(){
            return new Message(this);
        }
    }
    private Message(Builder builder){
        this.content = builder.content;
        this.sender = builder.sender;
        this.receiver = builder.receiver;
    }
    public Message(){}

    public static Builder builder(){
        return new Builder();
    }
}