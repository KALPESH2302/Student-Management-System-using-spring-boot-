package edu.cjc.main.repository;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cjc.main.model.*;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

	public Student findByUsernameAndPassword(String username,String password);

	public List<Student> findByBatchNumber(String batchNumber);

	public Slice<Student> findAllByBatchNumber(String batchNumber, Pageable p);
}
