package com.experiencers.server.smj.category;

import com.experiencers.server.smj.category.Category;
import com.experiencers.server.smj.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CategoryController{
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public ModelAndView getIndex(){
        List<Category> categoryList = categoryService.readAllCategory();

        ModelAndView response = new ModelAndView("category/list");
        response.addObject(categoryList);

        return response;
    }

    @PostMapping("/category")
    public String postCategory(@ModelAttribute Category inputtedCategory, HttpServletRequest request){
        System.out.println(inputtedCategory.toString());
        Category savedCategory = categoryService.writeCategory(inputtedCategory);

        return "redirect:/category";

    }
}
