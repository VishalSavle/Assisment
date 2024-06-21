package com.curd.dto;

import lombok.Data;

@Data
public class RegistrationDto {
	private String empCode;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
}
