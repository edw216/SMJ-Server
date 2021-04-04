package com.experiencers.server.smj.dto;

import com.experiencers.server.smj.domain.Alarm;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.enumerate.RepeatType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

public class AlarmDto {

    @Getter
    @Setter
    public static class AlarmDtoRequest {

        @ApiModelProperty(position = 1,notes = "알람 제목")
        private String title;

        @ApiModelProperty(position = 2,notes = "알람 내용")
        private String content;

        @ApiModelProperty(position = 3,notes = "날짜",example = "yyyy-MM-dd")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private String startDate;

        @ApiModelProperty(position = 4,notes = "시작시간",example = "HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
        private String startTime;

        @ApiModelProperty(position = 5,notes = "종료 시간",example = "HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
        private String endTime;

        @ApiModelProperty(position = 6,notes = "반복 주기",example = "ONCE, HOURLY, DAILY, MONTHLY, YEARLY")
        @Enumerated(EnumType.STRING)
        private RepeatType repeat;

        //Dto to Entity
        public Alarm toEntity(Member member){
            return Alarm.builder()
                    .title(this.title)
                    .content(this.content)
                    .startDate(this.startDate)
                    .startTime(this.startTime)
                    .endTime(this.endTime)
                    .repeat(this.repeat)
                    .member(member)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class AlarmDtoResponse {

        @ApiModelProperty(position = 1,notes = "알람 구분아이디")
        private Long id;

        @ApiModelProperty(position = 2,notes = "알람 제목")
        private String title;

        @ApiModelProperty(position = 3,notes = "알람 내용")
        private String content;

        @ApiModelProperty(position = 4,notes = "날짜",example = "yyyy-MM-dd")
        private String startDate;

        @ApiModelProperty(position = 5,notes = "시작시간",example = "HH:mm:ss")
        private String startTime;

        @ApiModelProperty(position = 6,notes = "종료 시간",example = "HH:mm:ss")
        private String endTime;

        @ApiModelProperty(position = 7,notes = "반복 주기",example = "ONCE, HOURLY, DAILY, MONTHLY, YEARLY")
        private RepeatType repeat;

        //Entity to Dto
        public static AlarmDtoResponse of(Alarm alarm){
            ModelMapper modelMapper = new ModelMapper();
            AlarmDtoResponse dto = modelMapper.map(alarm, AlarmDtoResponse.class);

             return dto;
        }

        //EntityList to DtoList
        public static List<AlarmDtoResponse> of(List<Alarm> alarmList){

            return alarmList.stream()
                    .map(AlarmDtoResponse::of)
                    .collect(Collectors.toList());
        }

    }

}
