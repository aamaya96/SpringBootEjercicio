package com.agilesolutions.exersice2.service;

import com.agilesolutions.exersice2.entity.Category;
import com.agilesolutions.exersice2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractService<Category,String>{

    @Autowired
    protected CategoryService(CategoryRepository repository) {
        super(repository);
    }
}
