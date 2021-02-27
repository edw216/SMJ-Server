package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category inputtedCategory){
        Category savedCategory = categoryRepository.save(inputtedCategory);

        return savedCategory;
    }

    public Category readCategory(Long categoryId){
        Category result = categoryRepository.findById(categoryId).get();

        return result;
    }

    public List<Category> readAllCategory(){
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long category_id){
        categoryRepository.deleteById(category_id);
    }

    public Category readAndUpdateCategory(Long categoryId, Category category){
        // 1. 저장된 데이터를 찾기
        // 2. 찾은 데이터의 값을 바꿔주기
        // 3. 바꾼 데이터를 다시 저장
        // - #중요: id가 바뀌면 새로 생성, 동일하면 수정
        /*Category data = categoryRepository.findById(category.getId()).get();
        data.setId(category.getId());
        data.setName(category.getName());

        categoryRepository.save(data);*/
        Optional<Category> data = categoryRepository.findById(categoryId);

        if (data.isPresent()) {
            Category target = data.get();
            target.setName(category.getName());
            target = categoryRepository.save(target);

            return target;

        }
        return null;

    }

}
