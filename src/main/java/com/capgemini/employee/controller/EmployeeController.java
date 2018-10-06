package com.capgemini.employee.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.employee.entity.Employee;
import com.capgemini.employee.exception.EmployeeNotFoundException;
import com.capgemini.employee.service.EmployeeService;



@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		ResponseEntity<Employee> responseEntity = 
				new ResponseEntity<Employee>(service.addEmployee(employee), HttpStatus.OK);
			return responseEntity;	
	}
	@PutMapping("/employee")
	public ResponseEntity<Employee> updateProduct(@RequestBody Employee employee) {
		try {
			Employee employeeId = service.findEmployeeById(employee.getEmployeeId());
			
			return new ResponseEntity<Employee>(service.updateEmployee(employee), HttpStatus.OK);
		                                                                                                                                                                          
		}
		catch(EmployeeNotFoundException exception) {
	}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int employeeId){
		try {
			Employee employee = service.findEmployeeById(employeeId);
			if(employee!= null) {
				service.deleteEmployee(employeeId);
				return new ResponseEntity<Employee>(HttpStatus.OK);
			}
		}
		catch(EmployeeNotFoundException exception) {
		}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable int employeeId) {
		try {
			Employee employee= service.findEmployeeById(employeeId);
			return new ResponseEntity<Employee>(service.findEmployeeById(employeeId), HttpStatus.OK);
		}
		catch(EmployeeNotFoundException exception) {
	}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
}
}

