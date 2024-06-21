package com.curd.servicesImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.curd.dto.LoginDto;
import com.curd.dto.RegistrationDto;
import com.curd.entity.Employee_Entity;
import com.curd.repository.Registration_Repo;
import com.curd.service.RegistrationService;
@Service
public class RegistrationServiceImp implements RegistrationService{
	@Autowired
	private Registration_Repo registration_Repo;

	@Override
	public ResponseEntity<String> Registration(RegistrationDto dto) {
		try {
			if(dto.getEmpCode()==null || dto.getEmpCode().trim().isEmpty()) {
				   return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee code is Empty");
			}
			Optional<Employee_Entity>userRegistration=registration_Repo.findByEmpCode(dto.getEmpCode());
			if(userRegistration.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee Code is Already Exists");
			}
			Optional<Employee_Entity>UserEmail=registration_Repo.findByemail(dto.getEmail());
			if(UserEmail.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is Already Exists");
			}
			Employee_Entity register = new Employee_Entity();
			register.setEmail(dto.getEmail());
			register.setEmpCode(dto.getEmpCode());
			register.setFirstName(dto.getFirstName());
			register.setLastName(dto.getLastName());
			register.setPhoneNumber(dto.getPhoneNumber());
			register.setPassword(dto.getPassword());
			registration_Repo.save(register);
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Employee created Successfully");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Employee Already Exist");
		}
	}

	@Override
	public ResponseEntity<String> UserLogin(LoginDto dto) {
		Optional<Employee_Entity>loginUser=registration_Repo.findByemail(dto.getEmail());
		try {
			if(!loginUser.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email not found");
			}
			else {
				Employee_Entity user=loginUser.get();
				if(user.getPassword().equals(dto.getPassword())) {
					return ResponseEntity.status(HttpStatus.OK).body("Login Successful");
				}else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Password");
				}
			}
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> deleteEmployee(String empCode) {
		Optional<Employee_Entity>empDelete=registration_Repo.findByEmpCode(empCode);
		try {
			if(!empDelete.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee is not found with this empCode: "+ empCode);
			}else {
				registration_Repo.delete(empDelete.get());
				 return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully with empCode:" + empCode);
			}
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while trying to delete the employee");
		}
	
	}

	@Override
	public ResponseEntity<String> updateEmployee(String empCode, RegistrationDto registrationDto) {
		Optional<Employee_Entity> empUpdate=registration_Repo.findByEmpCode(empCode);
		try {
			if(empUpdate.isEmpty() || empCode==null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee code is not found");
			}else {
				Employee_Entity user=empUpdate.get();
				user.setEmail(registrationDto.getEmail());
				user.setFirstName(registrationDto.getFirstName());
				user.setLastName(registrationDto.getLastName());
				user.setPhoneNumber(registrationDto.getPhoneNumber());
				registration_Repo.save(user);
				return ResponseEntity.status(HttpStatus.OK).body("Employee Updated Successful");
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Employee update Failed, Duplicate Entry Not allow");
		}
	}
	

	/*
	 * @Override public ResponseEntity<String> LoginRegistration(RegistrationDto
	 * dto) { try {
	 * Optional<Registration_Entity>existingEmpCode=registration_Repo.findByEmpCode(
	 * dto.getEmpCode()); if(existingEmpCode.isPresent()) { Registration_Entity
	 * matching_registration=existingEmpCode.get(); String
	 * email=matching_registration.getEmail(); String
	 * empCode=matching_registration.getEmpCode();
	 * 
	 * Optional<Login_Entity>existingEmpId=login_Repo.findByEmpId(empCode);
	 * if(existingEmpId.isPresent()) {
	 * ResponseEntity.status(HttpStatus.CONFLICT).body("Employee Already Exist"); }
	 * Login_Entity newUser=new Login_Entity(); newUser.setEmail(dto.getEmail());
	 * newUser.setPassword(dto.getPassword()); newUser.setEmpId(empCode);
	 * 
	 * login_Repo.save(newUser); return ResponseEntity.status(HttpStatus.CREATED).
	 * body("Login User Created successfully"); } }catch(Exception e) { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).
	 * body("login Registration Failed"); } return null; }
	 */

}
