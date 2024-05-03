package edu.cjc.main.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import edu.cjc.main.model.Student;
import edu.cjc.main.repository.StudentRepository;
import edu.cjc.main.service.*;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{

	@Autowired
	StudentRepository sr;
	@Override
	public Student findByUsernaemAndPassword(String usename, String password) {
		return sr.findByUsernameAndPassword(usename, password);
	}
	@Override
	public void save(Student s) {
		sr.save(s);
		
	}
	@Override
	public List<Student> findAll() {
		return sr.findAll();
	}
	@Override
	public List<Student> paging(int pageno, int pagesize) {
		Pageable p = PageRequest.of(pageno, pagesize,Sort.by("studentId").ascending());
        
		return sr.findAll(p).getContent();
	}
	
	@Override
	public int getTotalPages(int pageSize) {
	    int totalStudents = (int) sr.count();
	    return (int) Math.ceil((double) totalStudents / pageSize);
	}

}
