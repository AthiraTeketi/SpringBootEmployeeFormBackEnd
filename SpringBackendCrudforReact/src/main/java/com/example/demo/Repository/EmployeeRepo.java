package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Demo;

public interface EmployeeRepo extends JpaRepository<Demo, Integer> {

}
