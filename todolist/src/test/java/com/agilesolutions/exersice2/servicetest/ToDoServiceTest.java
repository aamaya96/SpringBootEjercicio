package com.agilesolutions.exersice2.servicetest;

import com.agilesolutions.exersice2.entity.Category;
import com.agilesolutions.exersice2.entity.ToDo;
import com.agilesolutions.exersice2.entity.User;
import com.agilesolutions.exersice2.service.CategoryService;
import com.agilesolutions.exersice2.service.ToDoService;
import com.agilesolutions.exersice2.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToDoServiceTest {

    User userTest;
    Category categoryTest;

    @Autowired
    ToDoService toDoService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Before
    public void createToDoTest(){
        userTest = userService.create(new User("userTestToDo","",""));
        categoryTest = categoryService.create( new Category("categoryTestToDo"));
        ToDo toDo = new ToDo(2,"title Test","", categoryTest, userTest);
        toDoService.create(toDo);
    }

    @Test
    public void whenGetAll_thenShouldReturnList(){
        assertThat(toDoService.getAll()).isNotEmpty();
    }

    @Test
    public void whenCreateToDo_thenReturnToDo(){
        ToDo toDo = new ToDo(3,"title Test","", categoryTest, userTest);
        assertThat(toDoService.create(toDo).getId()).isEqualTo(toDo.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void FindAToDoThatDoesNotExist(){
        toDoService.findById(99);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createToDoWhichUserDoesNotExist(){
        toDoService.create(new ToDo(4,"titleError","", categoryTest,new User()));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createToDoWhichCategoryDoesNotExist(){
        toDoService.create(new ToDo(4,"titleError","", new Category(),userTest));
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteToDoNotFound(){
        toDoService.delete(1);
        toDoService.delete(1);
    }


}
