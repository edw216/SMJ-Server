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
    @ApiOperation(value = "카테고리 목록",notes = "성공시 모든 카테고리를 반환합니다.",response = Category.class)
    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        List<Category> categoryList = categoryService.readAllCategory();

        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "작성됨")
    })
    @ApiOperation(value = "카테고리 생성",notes = "성공시 카테고리를 저장합니다.")
    @PostMapping("")
    // 성공: 201 Created
    public ResponseEntity<?> postCategories(@RequestBody CategoryDto categoryDto){
        Category savedCategory = categoryService.saveCategory(categoryDto);

        return new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiImplicitParam(name = "category_id",value = "카테고리번호",required = true,paramType = "path")
    @ApiOperation(value = "카테고리 수정",notes = "해당 카테고리의 내용을 변경합니다.")
    @PutMapping("/{category_id}")
    // 성공: 200 OK
    // 실패: 404 NOT FOUND
    public ResponseEntity<?> putCategories(@PathVariable("category_id") Long categoryId, @RequestBody CategoryDto categoryDto){
        Category updatedCategory = categoryService.readAndUpdateCategory(categoryId, categoryDto);

        if (updatedCategory == null) {
            return new ResponseEntity<>(categoryDto, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 204, message = "콘텐츠 없음")
    })
    @ApiImplicitParam(name = "category_id",value = "카테고리번호",required = true,paramType = "path")
    @ApiOperation(value = "카테고리 삭제",notes = "성공시 해당 카테고리를 삭제합니다.")
    @DeleteMapping("/{category_id}")
    public ResponseEntity<?> deleteCategories(@PathVariable("category_id") Long categoryId){
        categoryService.deleteCategory(categoryId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("category_id", categoryId);
        result.put("category", data);

        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }
}