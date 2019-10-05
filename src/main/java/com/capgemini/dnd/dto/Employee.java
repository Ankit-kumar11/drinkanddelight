package com.capgemini.dnd.dto;

import java.util.Date;

public class Employee {
	private String empId;
	private String empName;
	private String designation; // enum later
	private String gender; // enum later
	private Date dob;
	private String emailId;
	private String phoneNo;

	private String username;
	private String password;
	private String confirmPassword;
	private boolean loggedIn;
	
	private String securityQuestion;
	private String securityAnswer;

	public Employee() {
		
	}
	
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public Employee(String empName, String designation, String gender, Date dob, String emailId,
			String phoneNo, String username, String password, String confirmPassword, boolean loggedIn,
			String securityQuestion, String securityAnswer) {
		super();
		this.empName = empName;
		this.designation = designation;
		this.gender = gender;
		this.dob = dob;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.loggedIn = loggedIn;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", designation=" + designation + ", gender="
				+ gender + ", dob=" + dob + ", emailId=" + emailId + ", phoneNo=" + phoneNo + ", username=" + username
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", loggedIn=" + loggedIn
				+ ", securityQuestion=" + securityQuestion + ", securityAnswer=" + securityAnswer + "]";
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
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
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date date) {
		this.dob = date;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
