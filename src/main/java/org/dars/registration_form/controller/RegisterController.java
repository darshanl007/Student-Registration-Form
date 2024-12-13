package org.dars.registration_form.controller;

import org.dars.registration_form.dto.Student;
import org.dars.registration_form.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.RazorpayException;

import jakarta.validation.Valid;

@Controller
public class RegisterController {

	@Autowired
	StudentService service;

	@GetMapping("/")
	public String showForm(ModelMap map, Student student) {
		map.put("student", student);
		return "register.html";
	}

	@PostMapping("/register")
	public String register(@Valid Student student, BindingResult result, ModelMap map) {
		return service.register(student, result, map);
	}

	@GetMapping("/payment")
	public String payment() {
		return "payment.html";
	}

	@PostMapping("/payment")
	public String payment(@RequestParam int amount, ModelMap map) throws RazorpayException {
		return service.payment(amount, map);
	}

	@PostMapping("/success")
	public String success() {
		return "payment-success.html";
	}
}
