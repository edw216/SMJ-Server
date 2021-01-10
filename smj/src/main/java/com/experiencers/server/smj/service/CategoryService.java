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
//쓰는부분
    public Category writeCategory (Category inputtedCategory){
        Category savedCategory = categoryRepository.save(inputtedCategory);
        return savedCategory;
    }
    public List<Category> readAllBoard(){return categoryRepository.findAll();}

    public void removeCategory(Long category_id){
        categoryRepository.deleteById(category_id);
    }

    public void updateCategory(Category category){
        Category beforeCategory = categoryRepository.findById(category.getcategory_id()).get();
        beforeCategory.setTitle(category.getTitle());

        categoryRepository.save(beforeCategory);
    }
}
