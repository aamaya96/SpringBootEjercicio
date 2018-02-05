package com.agilesolutions.exersice2.controller;

import com.agilesolutions.exersice2.entity.Category;
import com.agilesolutions.exersice2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public List<Category> getAll(){
        return service.getAll();
    }

    @GetMapping("/categories/{categoryName}")
    public Category getCategoryById(@PathVariable  String categoryName){
        return service.findById(categoryName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category){
        return service.create(category);
    }

    @PutMapping("/categories")
    public Category updateCategory(@RequestBody Category category){
        return service.update(category);
    }

    @DeleteMapping("/categories/{categoryName}")
    public void deleteCategory(@PathVariable String categoryName){
        service.delete(categoryName);
    }

}
