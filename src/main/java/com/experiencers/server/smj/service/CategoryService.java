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
    public Category saveCategory(CategoryDto categoryDto){
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();
        Category savedCategory = categoryRepository.save(category);

        return savedCategory;
    }
    public Category readAndUpdateCategory(Long categoryId, CategoryDto categoryDto){
        Optional<Category> data = categoryRepository.findById(categoryId);

        if (data.isPresent()) {
            Category target = data.get();
            target.setName(categoryDto.getName());
            target = categoryRepository.save(target);

            return target;

        }
        return null;
    }
    //Api, Admin Service
    public List<Category> readAllCategory(){
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long category_id){
        categoryRepository.deleteById(category_id);
    }

    //Admin Service
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
