package com.experiencers.server.smj.dto;

import com.experiencers.server.smj.enumerate.RepeatType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@Getter
@Setter
public class AlarmDto {
    @ApiModelProperty(position = 1,notes = "알람 제목")
    private String title;

    @ApiModelProperty(position = 2,notes = "알람 내용")
    private String content;

    @ApiModelProperty(position = 3,notes = "날짜",example = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private Date day;

    @ApiModelProperty(position = 4,notes = "시작시간",example = "HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    private Time startTime;

    @ApiModelProperty(position = 5,notes = "종료 시간",example = "HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    private Time endTime;

    @ApiModelProperty(position = 6,notes = "반복 주기",example = "ONCE, HOURLY, DAILY, MONTHLY, YEARLY")
    @Enumerated(EnumType.STRING)
    private RepeatType repeat;


}
