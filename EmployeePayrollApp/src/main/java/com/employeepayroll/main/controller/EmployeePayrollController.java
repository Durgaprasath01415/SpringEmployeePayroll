package com.employeepayroll.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeepayroll.main.DTO.EmployeePayrollDTO;
import com.employeepayroll.main.DTO.ResponseDTO;
import com.employeepayroll.main.exceptions.EmployeePayrollException;
import com.employeepayroll.main.model.EmployeePayrollData;
import com.employeepayroll.main.service.IEmployeePayrollService;

@RestController
@RequestMapping("/employee")
public class EmployeePayrollController {
	@Autowired(required=true)
	private IEmployeePayrollService empService;
	@RequestMapping(value= {"","/","/get"})
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(){
		List<EmployeePayrollData> empDataList = null;
		empDataList = empService.getEmployeePayrolldata();
		ResponseDTO respDTO = new ResponseDTO("Get call success",empDataList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/get/{empId}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
		EmployeePayrollData empData =null;
		empData = empService.getEmployeePayrollDataById(empId);
		ResponseDTO respDTO = new ResponseDTO("Get call success for id: ",empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> addEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO){
		EmployeePayrollData empData = empService.createEmployeePayrollData(empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("Created Employee Payroll Data for: ",empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/update/{empiD}")
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,@Valid @RequestBody EmployeePayrollDTO empPayrollDTO){
		EmployeePayrollData empData = empService.updateEmployeePayrollData(empId,empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("Updated Employee Payroll Data for: ",empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/department/{department}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable ("department") String department) throws EmployeePayrollException{
		List<EmployeePayrollData>empData=null;
		empData=empService.getEmployeeByDepartment(department);
		ResponseDTO respDTO=new ResponseDTO("Department of Employee is :",empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId){
		empService.deleteEmployeePayrollData(empId);
		ResponseDTO respDTO = new ResponseDTO("Deleted Successfully ","Deleted id: "+empId);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
}
