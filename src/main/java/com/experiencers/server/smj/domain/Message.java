package com.experiencers.server.smj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    public Message(){
        final LocalDateTime t = LocalDateTime.now();
        date = t;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;
    @ApiModelProperty(example = "내용")
    @Column(nullable = false, length = 255)
    private String content;
    @JsonIgnore
    @Column( length = 50)
    private String sender;
    @ApiModelProperty(example = "example@example.com")
    @Column(nullable = false, length = 50)
    private String receiver;
    @CreationTimestamp
    private LocalDateTime date;


    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + messageId +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}