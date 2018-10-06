package com.capgemini.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
/*	
	public Employee addEmployee(Employee employee);
	public Employee updateEmployee(Employee employee);
	public Employee deleteEmployee(int employeeId);
	public Employee findAllEmployeeById(int employeeId);
*/
}
