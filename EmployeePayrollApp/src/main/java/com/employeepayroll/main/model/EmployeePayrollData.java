package com.employeepayroll.main.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


import com.employeepayroll.main.DTO.EmployeePayrollDTO;

import lombok.Data;

@Entity
@Table(name = "employeepayroll")
public @Data class EmployeePayrollData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="employee_id")
	private int employeeId;
	
	@Column(name="name")
	private String name;
	private double salary;
	private String gender;
	private LocalDate startDate;
	private String note;
	
	
	@ElementCollection
	@CollectionTable(name = "employeedepartment", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "department")
	private List<String> department;
	public EmployeePayrollData(){
	}
	
	public EmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		this.updateEmployeePayrolldata(employeeId, empPayrollDTO);
	}
	private void updateEmployeePayrolldata(int empId,EmployeePayrollDTO empPayrollDTO) {
		this.name=empPayrollDTO.name;
		this.salary=empPayrollDTO.salary;
		this.gender=empPayrollDTO.gender;
		this.note=empPayrollDTO.note;
		this.startDate=empPayrollDTO.startDate;
		this.department=empPayrollDTO.department;
		
	}
	
	public EmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
		super();
		this.employeeId = empId;
		this.name = empPayrollDTO.name;
		this.salary = empPayrollDTO.salary;
		this.gender = empPayrollDTO.gender;
		this.startDate = empPayrollDTO.startDate;
		this.note = empPayrollDTO.note;
		this.department =empPayrollDTO. department;
	}
	
	public EmployeePayrollData(int employeeId, EmployeePayrollDTO empPayrollDTO, int empId) {
		super();
		this.employeeId = empId;
		this.name = empPayrollDTO.name;
		this.salary = empPayrollDTO.salary;
		this.gender = empPayrollDTO.gender;
		this.startDate = empPayrollDTO.startDate;
		this.note = empPayrollDTO.note;
		this.department =empPayrollDTO. department;
	}
	
	@Override
	public String toString() {
		return "EmployeePayrollData [employeeId=" + employeeId + ", name=" + name + ", salary=" + salary + ", gender="
				+ gender + ", startDate=" + startDate + ", note=" + note + ", department=" + department + "]";
	}
	
}
