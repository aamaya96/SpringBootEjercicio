package com.agilesolutions.exersice2.servicetest;

import com.agilesolutions.exersice2.entity.User;
import com.agilesolutions.exersice2.service.UserService;
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
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Before
    public void createUserTest(){
        User user = new User("userTest", "userFirstNameUpdated", "userLastNameUpdated");
        userService.create(user);
    }

    @Test
    public void whenGetAll_thenShouldReturnList(){
        assertThat(userService.getAll()).isNotEmpty();
    }

    @Test
    public void whenCreateUser_thenReturnUser(){
        User user = new User("example", "exampleFirstName", "exampleLastName");
        assertThat(userService.create(user).getUserName()).isEqualTo(user.getUserName());
    }

    @Test
    public void whenUpdateUser_thenUserShouldBeReturned(){
        User user = new User("userTest", "userFirstNameUpdated", "userLastNameUpdated");
        assertThat(userService.update(user).getFirstName()).isEqualTo(user.getFirstName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void FindAnUserThatDoesNotExist(){
        userService.findById("notFound");
    }

    @Test(expected = JpaSystemException.class)
    public void createUserWhichIdIsNull(){
        userService.create(new User());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteUserNotFound(){
        userService.delete("userTest");
        userService.delete("userTest");
    }


}
