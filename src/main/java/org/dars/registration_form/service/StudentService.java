package org.dars.registration_form.service;

import org.dars.registration_form.dto.Student;
import org.dars.registration_form.helper.EmailHelper;
import org.dars.registration_form.repository.StudentRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.validation.Valid;

@Service
public class StudentService {

	@Autowired
	StudentRepository repository;

	@Autowired
	EmailHelper emailHelper;

	public String register(@Valid Student student, BindingResult result, ModelMap map) {
		if (!student.getPassword().equals(student.getConfirmPassword()))
			result.rejectValue("confirmPassword", "error.confirmPassword",
					"*Password and Confirm Password does not match");

		if (repository.existsByEmail(student.getEmail()))
			result.rejectValue("email", "error.email", "*Email already exists");

		if (result.hasErrors())
			return "register.html";

		else {
			repository.save(student);
			map.put("success", "Registered Successdully");
			emailHelper.sendEmail(student);
			return "register.html";
		}
	}

	public String payment(int amount, ModelMap map) throws RazorpayException {

		RazorpayClient razorpay = new RazorpayClient("rzp_test_LjeUdOv6BXQ9uv", "u8Q7IpDTKcedhm0wa14Ud4Mu");

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", amount * 100);
		orderRequest.put("currency", "INR");

		Order order = razorpay.orders.create(orderRequest);
		map.put("key", "rzp_test_LjeUdOv6BXQ9uv");
		map.put("amount", amount * 100);
		map.put("orderId", order.get("id"));
		return "razorpay.html";
	}	
}
