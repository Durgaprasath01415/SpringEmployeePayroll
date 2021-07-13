package com.employeepayroll.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employeepayroll.main.DTO.EmployeePayrollDTO;
import com.employeepayroll.main.exceptions.EmployeePayrollException;
import com.employeepayroll.main.model.EmployeePayrollData;
@Service
public interface IEmployeePayrollService {
	public List<EmployeePayrollData> getEmployeePayrolldata();
	public EmployeePayrollData getEmployeePayrollDataById(int empId)throws EmployeePayrollException;
	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO)throws EmployeePayrollException;
	public EmployeePayrollData updateEmployeePayrollData(int empId,EmployeePayrollDTO employeePayrollDTO) throws EmployeePayrollException;
	public List<EmployeePayrollData> getEmployeeByDepartment(String department)throws EmployeePayrollException;
	public void deleteEmployeePayrollData(int empId);
}
