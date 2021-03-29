package com.experiencers.server.smj.dto;

import com.experiencers.server.smj.domain.Setting;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

public class SettingDto {

    @Getter
    @Setter
    public static class SettingDtoRequest{
        @ApiModelProperty(position = 1,notes = "푸쉬 알림",example = "false")
        private boolean push;

        @ApiModelProperty(position = 2,notes = "GPS 수신상태",example = "true")
        private boolean gps;

    }

    @Getter
    @Setter
    public static class SettingDtoResponse{
        @ApiModelProperty(position = 1,notes = "설정 구분아이디")
        private Long id;

        @ApiModelProperty(position = 2,notes = "푸쉬 알림상태",example = "false")
        private boolean push;

        @ApiModelProperty(position = 3,notes = "GPS 수신상태",example = "true")
        private boolean gps;

        public static SettingDtoResponse of(Setting setting){
            ModelMapper modelMapper = new ModelMapper();
            SettingDtoResponse dto = modelMapper.map(setting,SettingDtoResponse.class);

            return dto;
        }
    }
}
