package com.experiencers.server.smj.api;


import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categorys")
public class CategoryApiController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<Category> getCategorys(@RequestHeader("Authorization")String token) {
        List<Category> categoryList = categoryService.readAllCategory();

        return categoryList;
    }

    @PostMapping("")
    // 성공: 201 Created
    public Category postCategory(@RequestHeader("Authorization")String token,@RequestBody Category category){
        Category savedCategory = categoryService.saveCategory(category);

        return savedCategory;
    }

    @PutMapping("/{category_id}")
    // 성공: 200 OK
    // 실패: 404 NOT FOUND
    public ResponseEntity<Category> putAlarm(@RequestHeader("Authorization")String token,@PathVariable("category_id") Long categoryId, @RequestBody Category category){
        Category updatedCategory = categoryService.readAndUpdateCategory(categoryId, category);

        if (updatedCategory == null) {
            category.setId(categoryId);
            return new ResponseEntity<>(category, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<Object> deleteCategory(@RequestHeader("Authorization")String token,@PathVariable("category_id") Long categoryId){
        categoryService.deleteCategory(categoryId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("category_id", categoryId);
        result.put("category", data);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}