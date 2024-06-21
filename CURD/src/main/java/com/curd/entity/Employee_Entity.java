package com.curd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "Employee")
public class Employee_Entity {
	@Id
	@SequenceGenerator(name="employee", initialValue = 101 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "emp_code", length = 50, nullable = false, unique = true)
	private String empCode;
	private String firstName;
	private String lastName;
	@Column(name = "email", length = 50, nullable = false, unique = true)
	private String email;
	private String phoneNumber;
	private String password;
}
