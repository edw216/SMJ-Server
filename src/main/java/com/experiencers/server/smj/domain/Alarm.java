package com.experiencers.server.smj.domain;

import com.experiencers.server.smj.enumerate.RepeatType;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Entity
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

    /*public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;

        if(!member.getAlarms().contains(this)){
            member.getAlarms().add(this);
        }
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

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


