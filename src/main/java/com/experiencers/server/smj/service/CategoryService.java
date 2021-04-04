package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.dto.CategoryDto;
import com.experiencers.server.smj.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //Api Service
    public List<CategoryDto.CategoryDtoResponse> readAllCategoryOfApi(){

        List<Category> categoryList = categoryRepository.findAll();

        return CategoryDto.CategoryDtoResponse.of(categoryList);
    }

    //Admin Service
    public List<Category> readAllCategory(){
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long category_id){
        categoryRepository.deleteById(category_id);
    }

    public Category readCategory(Long categoryId){
        Category result = categoryRepository.findById(categoryId).get();

        return result;
    }


    public Category saveCategoryOfMember(Category category){
        Category savedCategory = categoryRepository.save(category);

        return savedCategory;
    }
    public Category readAndUpdateCategoryOfMember(Long categoryId, Category category){
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
