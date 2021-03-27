package com.experiencers.server.smj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SettingDto {
    @ApiModelProperty(position = 1,notes = "푸쉬 알림",example = "false")
    private boolean push;

    @ApiModelProperty(position = 2,notes = "GPS 수신상태",example = "true")
    private boolean gps;
}
