package edu.cjc.main.serviceimpl;

import java.util.List;
import java.util.Optional;

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
	public int getTotalPages(int pageSize,String batchnumber) {
		if(!batchnumber.equals("All")) {
			return (int) Math.ceil((double) sr.TotalcountBybatchnumber(batchnumber)/ pageSize);
		}
	    return (int) Math.ceil((double) sr.Totalcount()/ pageSize);
	}
	
	@Override
	public List<Student> findByBatch(String batchNumber, int pageNo, int pageSize) {
		 Pageable p = PageRequest.of(pageNo,pageSize,Sort.by("studentId").ascending());		 
			return sr.findAllByBatchNumber(batchNumber,p).getContent();
	}
	@Override
	public void deleteById(int id) {
		sr.deleteById(id);
		
	}
	@Override
	public Student getStudent(int id) {
		Optional<Student> opt = sr.findById(id);
		return opt.get();
	}
	@Override
	public void updateStudentFess(int id, double ammount) {
		Optional<Student> opt = sr.findById(id);
	    Student st =  opt.get();
	   double oldamount  = Double.parseDouble(st.getFeesPaid())+ammount;
	    st.setFeesPaid(Double.toString(oldamount));
	    sr.save(st);
		
	}
	
	@Override
	public void updateBatch(int id, String batchNumber) {
		Optional<Student> opt = sr.findById(id);
	    Student st =  opt.get();
		st.setBatchNumber(batchNumber);
		sr.save(st);
	}
	
	
	
		
	
	

}
