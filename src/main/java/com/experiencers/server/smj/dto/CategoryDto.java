package com.experiencers.server.smj.dto;

import com.experiencers.server.smj.domain.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    @Getter
    @Setter
    public static class CategoryDtoResponse{
        @ApiModelProperty(position = 1,notes = "카테고리 구분아이디")
        public Long id;

        @ApiModelProperty(position = 2,notes = "카테고리 이름")
        private String name;

        public static CategoryDtoResponse of(Category category){
            ModelMapper modelMapper = new ModelMapper();
            CategoryDtoResponse dto = modelMapper.map(category, CategoryDtoResponse.class);

            return dto;

        }
        public static List<CategoryDtoResponse> of(List<Category> categoryList){

            return categoryList.stream()
                    .map(CategoryDtoResponse::of)
                    .collect(Collectors.toList());
        }

    }
}
