package edu.cjc.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cjc.main.model.*;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

	public Student findByUsernameAndPassword(String username,String password);
}
