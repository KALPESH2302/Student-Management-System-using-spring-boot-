package edu.cjc.main.service;
import edu.cjc.main.model.*;
public interface Service {
	public Student findByUsernaemAndPassword(String usename,String password);

	public void save(Student s);

}
