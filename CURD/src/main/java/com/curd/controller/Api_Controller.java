package com.curd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curd.dto.LoginDto;
import com.curd.dto.RegistrationDto;
import com.curd.service.RegistrationService;

@RestController
@RequestMapping("/curd/api")
public class Api_Controller {
	@Autowired
	private RegistrationService registrationService;
	@PostMapping("/addEmployee")
	public ResponseEntity<?>addEmployee(@RequestBody RegistrationDto registrationDto){
		return registrationService.Registration(registrationDto);	
	}

	@GetMapping("/loginEmployee")
	public ResponseEntity<?>Emp_Login(@RequestBody LoginDto loginDto){
		return registrationService.UserLogin(loginDto);
	}
	
	@PutMapping("/updateEmployee/{empCode}")
	public ResponseEntity<?>updateEmployee(@PathVariable String empCode,@RequestBody RegistrationDto registrationDto){
		return registrationService.updateEmployee(empCode, registrationDto);
	}
	
	@DeleteMapping("/deleteEmployee/{empCode}")
	public ResponseEntity<?>deleteEmployee(@PathVariable String empCode){
		return registrationService.deleteEmployee(empCode);
		
	}
}
