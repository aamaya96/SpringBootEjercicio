package com.agilesolutions.exersice2.controller;

import com.agilesolutions.exersice2.entity.ToDo;
import com.agilesolutions.exersice2.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private ToDoService service;

    @GetMapping("/to-do-lists")
    public List<ToDo> getAll(){
        return service.getAll();
    }

    @GetMapping("/to-do-lists/{id}")
    public ToDo getToDoById(@PathVariable int id){
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/to-do-lists")
    public ToDo createToDo(@RequestBody  ToDo toDo){
        return service.create(toDo);
    }

    @PutMapping("/to-do-lists")
    public ToDo updateToDo(@RequestBody  ToDo toDo){
        return service.update(toDo);
    }

    @DeleteMapping("/to-do-lists/{id}")
    public void deleteToDo(@PathVariable int id){
        service.delete(id);
    }

}
