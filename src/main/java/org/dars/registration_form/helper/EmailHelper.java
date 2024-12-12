package org.dars.registration_form.helper;

import org.dars.registration_form.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailHelper {
	
	@Autowired
	JavaMailSender sender;
	
	public void sendEmail(Student student) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom("darshanl1254@gmail.com","Head Master");
			helper.setText("😊😊😊😊😊 Admission Successfull!!! Thank you for Creating Account 😊😊😊😊😊");
			helper.setSubject("Registration Successfull");
			helper.setTo(student.getEmail());
		} catch(Exception e) {
			e.printStackTrace();
		}
		sender.send(message);
	}
}
