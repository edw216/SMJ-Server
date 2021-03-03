package com.experiencers.server.smj.domain;

import com.experiencers.server.smj.enumerate.RepeatType;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Entity
@ToString(exclude = "member")
@Table(name = "alarm")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alarm_id")
    private Long id;
    @Column(length = 255)
    private String title;
    @Column(length = 10000)
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    @Column(nullable = false)
    private Date day;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    @Column(nullable = false)
    private Time startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    @Column(nullable = false)
    private Time endTime;
    @Column(nullable = false)
    private String repeat; //Enum 타입

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Override
    public String toString() {
        return "Alarm{"+
                "alarm_id="+id+
                ", title='" +title+'\''+
                ", content='" +content+'\''+
                ", day='"+day+'\''+
                ", startTime='"+startTime+'\''+
                ", endTime='"+endTime+'\''+
                ", repeat='"+repeat+'\''+
                '}';

    }
}


