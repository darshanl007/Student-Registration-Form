package org.dars.registration_form.controller;

import org.dars.registration_form.dto.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

	@GetMapping("/")
	public String showForm(ModelMap map,Student student) {
		map.put("student", student);
		return "register.html";
	}
}
