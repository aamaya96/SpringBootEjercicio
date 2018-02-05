package com.agilesolutions.exersice2.service;

import com.agilesolutions.exersice2.entity.ToDo;
import com.agilesolutions.exersice2.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService extends AbstractService<ToDo,Integer>{
    @Autowired
    public ToDoService(ToDoRepository repository){
        super(repository);
    }
}
