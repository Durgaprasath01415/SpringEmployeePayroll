package com.employeepayroll.main.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeepayroll.main.DTO.EmployeePayrollDTO;
import com.employeepayroll.main.exceptions.EmployeePayrollException;
import com.employeepayroll.main.model.EmployeePayrollData;
import com.employeepayroll.main.repository.IEmpRepository;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {
	@Autowired
	private IEmpRepository employeeRepository;
	
	@Override
	public List<EmployeePayrollData> getEmployeePayrolldata() {
		return employeeRepository.findAll();
	}
	
	@Override
	public EmployeePayrollData getEmployeePayrollDataById(int empId) {
		return employeeRepository.findById(empId).orElseThrow(()->new EmployeePayrollException("Employee Not Found"));
	}
	
	@Override
	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = null;
		empData = new EmployeePayrollData(empPayrollDTO);
		BeanUtils.copyProperties(empPayrollDTO,empData);
		return employeeRepository.save(empData);
	}
	@Override
	public EmployeePayrollData updateEmployeePayrollData(int empId,EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empdata=this.getEmployeePayrollDataById(empId);
		empdata.setName(empPayrollDTO.getName());
		empdata.setSalary(empPayrollDTO.getSalary());
		empdata.setGender(empPayrollDTO.getGender());
		empdata.setNote(empPayrollDTO.getNote());
		empdata.setStartDate(empPayrollDTO.getStartDate());
		empdata.setDepartment(empPayrollDTO.getDepartment());
		employeeRepository.save(empdata);
		return employeeRepository.findById(empId).get()	;	
	}
	
	@Override
	public void deleteEmployeePayrollData(int empId) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		employeeRepository.delete(empData);
	}
	
	@Override
	public List<EmployeePayrollData> getEmployeeByDepartment(String department) throws EmployeePayrollException{
		return employeeRepository.findEmployeeByDepartment(department);
		
	}

	
}
