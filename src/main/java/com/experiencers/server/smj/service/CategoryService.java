package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category writeCategory(Category inputtedCategory){
        Category savedCategory = categoryRepository.save(inputtedCategory);

        return savedCategory;
    }

    public Category readCategory(Long categoryId){
        Category result = categoryRepository.getOne(categoryId);

        return result;
    }

    public List<Category> readAllCategory(){
        List<Category> result = categoryRepository.findAll();

        return result;
    }

    public void removeCategory(Long category_id){
        categoryRepository.deleteById(category_id);
    }

    public void updateCategory(Category category){
        // 1. 저장된 데이터를 찾기
        // 2. 찾은 데이터의 값을 바꿔주기
        // 3. 바꾼 데이터를 다시 저장
        // - #중요: id가 바뀌면 새로 생성, 동일하면 수정
        Category data = categoryRepository.findById(category.getId()).get();
        data.setId(category.getId());
        data.setName(category.getName());

        categoryRepository.save(data);
    }

    public void updateCategory(Long categoryId, Category category) {
        category.setId(categoryId);
        categoryRepository.save(category);
    }
}
