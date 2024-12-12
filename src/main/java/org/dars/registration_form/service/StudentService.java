package org.dars.registration_form.service;

import org.dars.registration_form.dto.Student;
import org.dars.registration_form.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

@Service
public class StudentService {

	@Autowired
	StudentRepository repository;

	public String register(@Valid Student student, BindingResult result, ModelMap map) {
		if (!student.getPassword().equals(student.getConfirmPassword()))
			result.rejectValue("confirmPassword", "error.confirmPassword",
					"Password and Confirm Password does not match");

		if (repository.existsByEmail(student.getEmail()))
			result.rejectValue("email", "error.email", "Email already exists");

		if (result.hasErrors())
			return "register.html";

		else {
			repository.save(student);
			map.put("success", "Registered Successdully");
			return "register.html";
		}
	}
}
