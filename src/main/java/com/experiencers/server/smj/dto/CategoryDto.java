package com.experiencers.server.smj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    @ApiModelProperty(position = 1,notes = "카테고리 이름")
    private String name;
}
