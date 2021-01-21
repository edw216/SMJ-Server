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
    public Category readCategory(Long category_id){return categoryRepository.getOne(category_id);}

    public List<Category> readAllCategory(){return categoryRepository.findAll();}

    public void removeCategory(Long category_id){
        categoryRepository.deleteById(category_id);
    }

    public void updateCategory(Category category){
//        Category beforeCategory = categoryRepository.findById(category.()).get();
//        beforeCategory.setUser(category.getUser());
//        beforeCategory.setCategory(category.getCategory());

//        categoryRepository.save(beforeCategory);
    }
}
