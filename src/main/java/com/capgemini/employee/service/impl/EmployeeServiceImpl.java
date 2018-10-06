package com.capgemini.employee.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.employee.entity.Employee;
import com.capgemini.employee.exception.EmployeeNotFoundException;
import com.capgemini.employee.repository.EmployeeRepository;
import com.capgemini.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(int employeeId) throws EmployeeNotFoundException {
		  employeeRepository.deleteById(employeeId);
	
	}

	@Override
	public Employee findEmployeeById(int employeeId) throws EmployeeNotFoundException {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		if(optionalEmployee.isPresent())
			return optionalEmployee.get();
		throw new EmployeeNotFoundException("Employee does not exists");
	}

}
