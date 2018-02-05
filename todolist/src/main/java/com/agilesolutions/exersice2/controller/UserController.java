package com.agilesolutions.exersice2.controller;

import com.agilesolutions.exersice2.entity.ToDo;
import com.agilesolutions.exersice2.entity.User;
import com.agilesolutions.exersice2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return service.getAll();
    }

    @GetMapping("/users/{userName}")
    public User getUserById(@PathVariable String userName){
        return service.findById(userName);
    }

    @GetMapping("/users/{userName}/to-do-list")
    public List<ToDo> getToDoListByUser(@PathVariable String userName){
        return service.getToDoListByUser(userName);
    }

    @GetMapping("/users/{userName}/to-do-list/{categoryName}")
    public List<ToDo> getToDoListByUserCategory(@PathVariable String userName,@PathVariable String categoryName){
        return service.getToDoListByUserCategory(userName,categoryName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return service.create(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        return service.update(user);
    }

    @DeleteMapping("/users/{userName}")
    public void deleteUser(@PathVariable String userName){
        service.delete(userName);
    }
}
