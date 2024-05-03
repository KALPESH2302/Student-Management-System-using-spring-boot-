package edu.cjc.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.main.model.Student;
import edu.cjc.main.service.Service;

@Controller
public class AdminController {
    
	@Autowired
	Service sc;
	
	@RequestMapping("/")
	public String perLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam String username,@RequestParam String password,Model m) {
		username  = username.trim();
		password = password.trim();
		if(username.equals("admin") && password.equals("admin")) {
			return "adminscreen";
		}
		Student s = sc.findByUsernaemAndPassword(username, password);
         if(s != null){
			
			m.addAttribute("Student", s);
			return "studentview";
		}
		m.addAttribute("login_fail", "Wrong username and password !!!");
		return "login";
	}
	
	@RequestMapping("/enroll_student")
	public String save(@ModelAttribute Student s) {
		sc.save(s);
		return "adminscreen";
	}
}
