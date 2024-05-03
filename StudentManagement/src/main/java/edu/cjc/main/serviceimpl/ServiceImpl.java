package edu.cjc.main.serviceimpl;
import org.springframework.beans.factory.annotation.Autowired;

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

}
