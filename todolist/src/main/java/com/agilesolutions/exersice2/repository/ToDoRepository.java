package com.agilesolutions.exersice2.repository;

import com.agilesolutions.exersice2.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo,Integer> {

}
