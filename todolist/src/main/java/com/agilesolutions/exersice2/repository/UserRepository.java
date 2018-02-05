package com.agilesolutions.exersice2.repository;

import com.agilesolutions.exersice2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
