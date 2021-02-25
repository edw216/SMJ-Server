package com.experiencers.server.smj.controller;

import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController{
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ModelAndView getCategories(){
        List<Category> categories = categoryService.readAllCategory();

        ModelAndView response = new ModelAndView("category/index");
        response.addObject("categories", categories);

        return response; // category를 연결하면
    }

    @PostMapping("")
    public String postCategory(@ModelAttribute Category inputtedCategory) {
        System.out.println(inputtedCategory.toString());
        Category savedCategory = categoryService.saveCategory(inputtedCategory);

        return "redirect:/category";
    }

    @GetMapping("/{category_id}/edit")
    public ModelAndView editCategory(@PathVariable("category_id") Long categoryId) {
        Category category = categoryService.readCategory(categoryId);

        ModelAndView mav = new ModelAndView("category/edit");
        mav.addObject("category", category);
        return mav;
    }

    @PostMapping("/{category_id}/update")
    public String updateCategory(@PathVariable("category_id") Long categoryId,
                                       @ModelAttribute Category category) {
        categoryService.readAndUpdateCategory(categoryId, category);

        return "redirect:/category";
    }

    @PostMapping("/{category_id}/delete")
    public String deleteCategory(@PathVariable("category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);

        return "redirect:/category";
    }
}
