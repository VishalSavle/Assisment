package com.curd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curd.entity.Employee_Entity;


public interface Registration_Repo extends JpaRepository<Employee_Entity, Integer> {
	Optional<Employee_Entity> findByEmpCode(String empCode);
	Optional<Employee_Entity> findByemail(String email);
}