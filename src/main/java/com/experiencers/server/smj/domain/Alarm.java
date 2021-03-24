package com.experiencers.server.smj.domain;

import com.experiencers.server.smj.enumerate.RepeatType;
import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(position = 1,notes = "알람 아이디")
    private Long id;

    @ApiModelProperty(position = 2,notes = "알람 제목")
    @Column(length = 255)
    private String title;

    @ApiModelProperty(position = 3,notes = "알람 내용")
    @Column(length = 10000)
    private String content;

    @ApiModelProperty(position = 4,notes = "날짜",example = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    @Column(nullable = false)
    private Date day;

    @ApiModelProperty(position = 5,notes = "시작시간",example = "HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    @Column(nullable = false)
    private Time startTime;

    @ApiModelProperty(position = 6,notes = "종료 시간",example = "HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    @Column(nullable = false)
    private Time endTime;

    @ApiModelProperty(position = 7,notes = "반복 주기",example = "ONCE, HOURLY, DAILY, MONTHLY, YEARLY")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RepeatType repeat; //Enum 타입

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Override
    public String toString() {
        return "Alarm{"+
                "id="+id+
                ", title='" +title+'\''+
                ", content='" +content+'\''+
                ", day='"+day+'\''+
                ", startTime='"+startTime+'\''+
                ", endTime='"+endTime+'\''+
                ", repeat='"+repeat+'\''+
                '}';

    }
    public static class Builder{
        private String title;
        private String content;
        private Date day;
        private Time startTime;
        private Time endTime;
        private RepeatType repeat;
        private Member member;

        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Builder day(Date day){
            this.day = day;
            return this;
        }
        public Builder startTime(Time startTime){
            this.startTime = startTime;
            return this;
        }
        public Builder endTime(Time endTime){
            this.endTime = endTime;
            return this;
        }
        public Builder repeat(RepeatType repeat){
            this.repeat = repeat;
            return this;
        }
        public Builder member(Member member){
            this.member = member;
            return this;
        }
        public Alarm build(){
            return new Alarm(this);
        }

    }
    private Alarm(Builder builder){
        this.title = builder.title;
        this.content = builder.content;
        this.day = builder.day;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.repeat = builder.repeat;
        this.member = builder.member;
    }
    public Alarm(){}

    public static Builder builder(){
        return new Builder();
    }
}


