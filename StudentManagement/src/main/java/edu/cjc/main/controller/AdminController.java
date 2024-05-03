package edu.cjc.main.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

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
			List<Student>list = sc.findAll();
			m.addAttribute("data",list);
			return "adminscreen";
		}
		Student s = sc.findByUsernaemAndPassword(username, password);
         if(s != null){
			
			m.addAttribute("stu", s);
			return "studentview";
		}
		m.addAttribute("login_fail", "Wrong username and password !!!");
		return "login";
	}
	
	@RequestMapping("/enroll_student")
	public String save(@ModelAttribute Student s,Model m) {
		try {
			sc.save(s);
			
		}catch(Exception e) {
			m.addAttribute("msg", "emailid is alredy used ");
		}
		
		List<Student>list = sc.findAll();
		m.addAttribute("data",list);
		return "adminscreen";
	}
}
