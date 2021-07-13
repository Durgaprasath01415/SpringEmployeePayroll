package com.employeepayroll.main.service;

import java.util.ArrayList;
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
	private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
	
	@Override
	public EmployeePayrollData getEmployeePayrollDataById(int empId) {
		return employeePayrollList.stream().filter(empData->(empData.getEmployeeId()==empId))
				.findFirst().orElseThrow(()->new EmployeePayrollException("Employee Not Found"));
	}
	
	@Override
	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = new EmployeePayrollData();
		empData.getName();
		BeanUtils.copyProperties(empPayrollDTO,empData);
		return employeeRepository.save(empData);
	}
	@Override
	public EmployeePayrollData updateEmployeePayrollData(int empId,EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		empData.setName(empPayrollDTO.getName());
		empData.setSalary(empPayrollDTO.getSalary());
		empData.setGender(empPayrollDTO.getGender());
		empData.setNote(empPayrollDTO.getNote());
		empData.setStartDate(empPayrollDTO.getStartDate());
		empData.setDepartment(empPayrollDTO.getDepartment());
		employeePayrollList.set(empId, empData);
		return empData;
	}
	
	@Override
	public void deleteEmployeePayrollData(int empId) {
		employeePayrollList.remove(empId-1);
	}
	
	@Override
	public List<EmployeePayrollData> getEmployeeByDepartment(String department) throws EmployeePayrollException{
		return employeeRepository.findEmployeeByDepartment(department);
		
	}

	@Override
	public List<EmployeePayrollData> getEmployeePayrolldata() {
		return employeePayrollList;
		//return null;
	}
}
