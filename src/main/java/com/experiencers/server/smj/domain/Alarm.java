package com.experiencers.server.smj.domain;

import com.experiencers.server.smj.enumerate.RepeatType;
import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @NotNull
    @Column(nullable = false)
    private String startDate;

    @NotNull
    @Column(nullable = false)
    private String startTime;

    @NotNull
    @Column(nullable = false)
    private String endTime;

    @NotNull
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
                ", startDate='"+ startDate +'\''+
                ", startTime='"+startTime+'\''+
                ", endTime='"+endTime+'\''+
                ", repeat='"+repeat+'\''+
                '}';

    }
    public static class Builder{
        private String title;
        private String content;
        private String startDate;
        private String startTime;
        private String endTime;
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
        public Builder startDate(String startDate){
            this.startDate = startDate;
            return this;
        }
        public Builder startTime(String startTime){
            this.startTime = startTime;
            return this;
        }
        public Builder endTime(String endTime){
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
        this.startDate = builder.startDate;
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


