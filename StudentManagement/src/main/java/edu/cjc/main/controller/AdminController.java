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
    static int currentpage=0;
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
			return this.paging(0, m);
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
		
		return this.paging(0, m);
	}
	@RequestMapping("/pagging")
	public String paging(@RequestParam("pageNo") int pageno,Model m) {
		int pagesize=2;
		int noofpage = sc.getTotalPages(pagesize);
		System.out.println("noofpage : "+noofpage+"   "+"currentpage : "+currentpage);
		if(pageno==-111) {
			if(currentpage!=0) {
				
				currentpage = currentpage-1;
			}	
			
		}else if(pageno==111) {
			if(currentpage!=noofpage) {
				
				currentpage = currentpage+1;
						
		}	
	   }else {
		   currentpage=pageno;
	   }
		List<Student> list = sc.paging(currentpage, pagesize);
		m.addAttribute("data",list);
		m.addAttribute("totalpage",noofpage);
		return "adminscreen";
	}
}
