package com.experiencers.server.smj.api;


import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.dto.CategoryDto;
import com.experiencers.server.smj.service.CategoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "Categories", description = "카테고리")
@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {
    @Autowired
    private CategoryService categoryService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiOperation(value = "카테고리 목록",notes = "성공시 모든 카테고리를 반환합니다.",response = CategoryDto.CategoryDtoResponse.class)
    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        List<CategoryDto.CategoryDtoResponse> categoryList = categoryService.readAllCategoryOfApi();

        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

}