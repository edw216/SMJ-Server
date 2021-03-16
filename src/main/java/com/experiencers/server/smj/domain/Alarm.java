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
    private Long id;
    @ApiModelProperty(example = "알람제목")
    @Column(length = 255)
    private String title;
    @ApiModelProperty(example = "알람내용")
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
    @ApiModelProperty(example = "ONCE, HOURLY, DAILY, MONTHLY, YEARLY")
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
}


