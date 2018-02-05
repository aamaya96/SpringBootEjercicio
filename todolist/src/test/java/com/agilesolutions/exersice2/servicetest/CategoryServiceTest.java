package com.agilesolutions.exersice2.servicetest;

import com.agilesolutions.exersice2.entity.Category;
import com.agilesolutions.exersice2.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Before
    public void createCategoryTest(){
        Category category = new Category("testCategory");
        categoryService.create(category);
    }

    @Test
    public void whenGetAll_thenShouldReturnList(){
        assertThat(categoryService.getAll()).isNotEmpty();
    }

    @Test
    public void whenCreateCategory_thenReturnCategory(){
        Category category = new Category("example");
        assertThat(categoryService.create(category).getCategoryName()).isEqualTo(category.getCategoryName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void FindACategoryThatDoesNotExist(){
        categoryService.findById("notFound");
    }

    @Test(expected = JpaSystemException.class)
    public void createCategoryWichIdIsNull(){
        categoryService.create(new Category());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteCategoryNotFound(){
        categoryService.delete("categoryTest");
        categoryService.delete("categoryTest");
    }

}