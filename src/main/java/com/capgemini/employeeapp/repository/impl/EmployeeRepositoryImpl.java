package com.capgemini.employeeapp.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.employeeapp.entities.Employee;
import com.capgemini.employeeapp.repository.EmployeeRepository;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
    
	@Override
	public Employee addEmployee(Employee employee) {
		jdbcTemplate.update("INSERT INTO employees VALUES(?,?,?,?)", new Object[] {employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeDepartment(),employee.getEmployeeSalary()});
		return employee;
		
	}
    @Override
	public Employee updateEmployee(Employee employee) {
		jdbcTemplate.update("UPDATE employees SET employee_Name = ?, employee_Department = ?, employee_Salary = ? WHERE employee_Id=?", new Object[] {employee.getEmployeeName(), employee.getEmployeeDepartment(), employee.getEmployeeSalary(), employee.getEmployeeId()});
		return employee;
	}
    @Override
	public boolean deleteEmployee(int employeeId) {
		int count = jdbcTemplate.update("DELETE FROM employees WHERE employee_Id = ?", new Object[] {employeeId});
		return count!=0;
	}
    @Override
	public Employee findEmployeeById(int employeeId) {
		return jdbcTemplate.queryForObject("SELECT * FROM employees WHERE employee_Id=?", new Object[] {employeeId}, new EmployeeRowMapper());
	}
    @Override
	public List<Employee> findAllEmployees() {
		return jdbcTemplate.query("SELECT * FROM employees", new Object[] {}, new EmployeeRowMapper());
		
	}
	
	
	private class EmployeeRowMapper implements RowMapper<Employee>{
		
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException{
		    Employee employee = new Employee();
			employee.setEmployeeId(rs.getInt(1));
			employee.setEmployeeName(rs.getString(2));
			employee.setEmployeeDepartment(rs.getString(3));
			employee.setEmployeeSalary(rs.getDouble(4));
			return employee;
			
		}
		}

}
