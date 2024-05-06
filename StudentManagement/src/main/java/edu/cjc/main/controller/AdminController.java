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
	static String currentbatchnumber = "All";
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
		int noofpage = sc.getTotalPages(pagesize,currentbatchnumber);
		System.out.println(currentbatchnumber);
		System.out.println("noofpage : "+noofpage+"   "+"currentpage : "+currentpage);
		if(pageno==-111) {
			if(currentpage>0) {
				
				currentpage = currentpage-1;
			}	
			
		}else if(pageno==111) {
			if(currentpage<noofpage-1) {
				
				currentpage = currentpage+1;
						
		}else if(currentpage>=pageno) {
			currentpage = noofpage-1;
		}
			
	   }else {
		   currentpage=pageno;
	   }
		if(!currentbatchnumber.equals("All")) {
			return this.Bybatchnumber(currentbatchnumber, m, currentpage);
		}
		List<Student> list = sc.paging(currentpage, pagesize);
		
		if(list.isEmpty()) {
			list = sc.paging(pageno,pagesize);
			currentpage = pageno;
			m.addAttribute("message","no next data exist !!");
		}
		m.addAttribute("data",list);
		m.addAttribute("totalpage",noofpage);
		return "adminscreen";
	}
	

	@RequestMapping("/search")
	public String search(@RequestParam("batchNumber") String batchNumber,Model m,@RequestParam("pageNo")int pageNo) {
		currentbatchnumber = batchNumber;
		if(batchNumber.equals("All")) {
		
			return this.paging(0, m);
		}
		currentpage=0;
		System.out.println("pageno : "+pageNo+"  currentpage : "+currentpage);
		List<Student> list = sc.findByBatch(batchNumber,pageNo,2);
		m.addAttribute("data",list);		
		if(list.isEmpty()) {
			m.addAttribute("message","there is no student enroll for this batch");
		}
		
		return "adminscreen";	
	}
	
	public String Bybatchnumber(@RequestParam("batchNumber") String batchNumber,Model m,@RequestParam("pageNo")int pageNo) {
		currentbatchnumber = batchNumber;
		if(batchNumber.equals("All")) {
		
			return this.paging(0, m);
		}
		System.out.println("pageno : "+pageNo+"  currentpage : "+currentpage);
		List<Student> list = sc.findByBatch(batchNumber,pageNo,2);
		m.addAttribute("data",list);		
		if(list.isEmpty()) {
			m.addAttribute("message","there is no student enroll for this batch");
		}
		
		return "adminscreen";	
	}
	
	
	@RequestMapping("/removeStudent")
	public String remove(@RequestParam("id") int id,Model m) {
		sc.deleteById(id);
		int pagesize=2;
		int noofpage = sc.getTotalPages(pagesize,currentbatchnumber);
		if(currentpage>=noofpage) {
			currentpage=currentpage-1;
		}
		return this.paging(currentpage, m);
	}
	
	@RequestMapping("/fees")
	public String fees(@RequestParam int studentId,Model m) {
		Student s = sc.getStudent(studentId);
		m.addAttribute("st",s);
		return "fess";
	}
	
	@RequestMapping("/payfees")
	public String payFees(@RequestParam int studentid,@RequestParam double ammount,Model m ) {
		
		    sc.updateStudentFess(studentid,ammount);
		return this.paging(currentpage, m);
	}
	
	@RequestMapping("/batch")
	public String batch(@RequestParam int studentId,Model m) {
		Student s = sc.getStudent(studentId);
		m.addAttribute("st",s);
		return "batch";
	}
	
	@RequestMapping("/updateBatch")
	public String update(@RequestParam int studentid,@RequestParam String batchNumber,Model m) {
		sc.updateBatch(studentid,batchNumber);
		return this.paging(currentpage,m);
	}
}
