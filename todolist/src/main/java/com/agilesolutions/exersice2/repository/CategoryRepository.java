package com.agilesolutions.exersice2.repository;

import com.agilesolutions.exersice2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
