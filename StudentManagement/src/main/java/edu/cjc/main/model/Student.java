package edu.cjc.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Student {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int studentId;
private String studentFullName;
@Column(unique = true)   
private String studentEmail;
private int studentAge;
private String  studentCollegeName;
private String studentCourse;
private String batchNumber;
private String batchMode;
private String feesPaid;
private String username;
private String password;
public int getStudentId() {
	return studentId;
}
public void setStudentId(int studentId) {
	this.studentId = studentId;
}
public String getStudentFullName() {
	return studentFullName;
}
public void setStudentFullName(String studentFullName) {
	this.studentFullName = studentFullName;
}
public String getStudentEmail() {
	return studentEmail;
}
public void setStudentEmail(String studentEmail) {
	this.studentEmail = studentEmail;
}
public int getStudentAge() {
	return studentAge;
}
public void setStudentAge(int studentAge) {
	this.studentAge = studentAge;
}

public String getStudentCourse() {
	return studentCourse;
}
public void setStudentCourse(String studentCourse) {
	this.studentCourse = studentCourse;
}
public String getBatchNumber() {
	return batchNumber;
}
public void setBatchNumber(String batchNumber) {
	this.batchNumber = batchNumber;
}
public String getBatchMode() {
	return batchMode;
}
public void setBatchMode(String batchMode) {
	this.batchMode = batchMode;
}
public String getFeesPaid() {
	return feesPaid;
}
public void setFeesPaid(String feesPaid) {
	this.feesPaid = feesPaid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getStudentCollegeName() {
	return studentCollegeName;
}
public void setStudentCollegeName(String studentCollegeName) {
	this.studentCollegeName = studentCollegeName;
}
}
