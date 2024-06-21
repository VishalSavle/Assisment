package com.curd.service;

import org.springframework.http.ResponseEntity;

import com.curd.dto.LoginDto;
import com.curd.dto.RegistrationDto;

public interface RegistrationService {
	ResponseEntity<String>Registration(RegistrationDto dto);
	ResponseEntity<String>UserLogin(LoginDto dto);
	ResponseEntity<String>updateEmployee(String empCode,RegistrationDto registrationDto);
	ResponseEntity<String>deleteEmployee(String empCode);
}
