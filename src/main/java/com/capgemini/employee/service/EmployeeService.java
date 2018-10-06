package com.capgemini.employee.service;

import com.capgemini.employee.entity.Employee;
import com.capgemini.employee.exception.EmployeeNotFoundException;

public interface EmployeeService {
	
	public Employee addEmployee(Employee employee);
	public Employee updateEmployee(Employee employee)throws EmployeeNotFoundException;
	public void deleteEmployee(int employeeId) throws EmployeeNotFoundException;
	public Employee findEmployeeById(int employeeId) throws EmployeeNotFoundException;
}
