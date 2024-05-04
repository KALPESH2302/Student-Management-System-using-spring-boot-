package edu.cjc.main.service;
import java.util.List;

import edu.cjc.main.model.*;
public interface Service {
	public Student findByUsernaemAndPassword(String usename,String password);

	public void save(Student s);

	public List<Student> findAll();
	
	public List<Student> paging(int pageno,int pagesize);

	public int getTotalPages(int pageSize);

	public List<Student> findByBatch(String batchNumber);

}
