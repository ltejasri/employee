package com.capgemini.employee;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.employee.controller.EmployeeController;
import com.capgemini.employee.entity.Employee;
import com.capgemini.employee.service.EmployeeService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	@Mock
	public EmployeeService service;

	@InjectMocks
	private EmployeeController employeeController;
	private MockMvc mockMvc;
	Employee employee;

	@Before
	public void Setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		employee = new Employee();
		employee.setEmployeeId(123);
		employee.setEmployeeName("sri");
		employee.setEmployeeDepartment("javacloud");
		employee.setEmployeeSalary(100000);

	}

	@Test
	public void testaddEmployee() throws Exception {

		when(service.addEmployee(Mockito.isA(Employee.class))).thenReturn(employee);

		mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON_UTF8).content(
				"{\"employeeId\":\"123\",\"employeeName\":\"sri\",\"employeeDepartment\":\"javacloud\",\"employeeSalary\":\"100000\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.employeeId").exists()).andExpect(jsonPath("$.employeeName").exists())
				.andExpect(jsonPath("$.employeeDepartment").exists()).andExpect(jsonPath("$.employeeSalary").exists());
	}

	@Test
	public void testupdateEmployee() throws Exception {

		when(service.findEmployeeById(123)).thenReturn(employee);

		employee.setEmployeeSalary(50000);

		when(service.updateEmployee(Mockito.isA(Employee.class))).thenReturn(employee);

		mockMvc.perform(put("/employee").contentType(MediaType.APPLICATION_JSON_UTF8).content(
				"{\"employeeId\":\"123\",\"employeeName\":\"sri\",\"employeeDepartment\":\"javacloud\",\"employeeSalary\":\"50000\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.employeeId").exists()).andExpect(jsonPath("$.employeeName").exists())
				.andExpect(jsonPath("$.employeeDepartment").exists()).andExpect(jsonPath("$.employeeSalary").exists())
				.andDo(print());
	}

	@Test
	public void testfindEmployeeById() throws Exception {

		when(service.findEmployeeById(123)).thenReturn(employee);

		mockMvc.perform(get("/employee/123").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.employeeId").exists()).andExpect(jsonPath("$.employeeName").exists())
				.andExpect(jsonPath("$.employeeDepartment").exists()).andExpect(jsonPath("$.employeeSalary").exists())
				.andDo(print());
	}

	@Test
	public void testdeleteEmployee() throws Exception {

		when(service.findEmployeeById(123)).thenReturn(employee);
		mockMvc.perform(delete("/employee/123").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
}
