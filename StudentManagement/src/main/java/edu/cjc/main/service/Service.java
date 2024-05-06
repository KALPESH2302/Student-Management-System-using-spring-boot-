package edu.cjc.main.service;
import java.util.List;

import edu.cjc.main.model.*;
public interface Service {
	public Student findByUsernaemAndPassword(String usename,String password);

	public void save(Student s);

	public List<Student> findAll();
	
	public List<Student> paging(int pageno,int pagesize);

	public int getTotalPages(int pageSize, String currentbatchnumber);

	List<Student> findByBatch(String batchNumber, int pageNo, int pageSize);

	public void deleteById(int id);

	public Student getStudent(int id);

	public void updateStudentFess(int studentId, double ammount);


	public void updateBatch(int studentid, String batchNumber);

}
