package com.global.hr.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.global.hr.mapper.EmployeeMapper;
import com.global.hr.model.Employee;
import com.global.hr.repository.EmployeeReps;


@Component
public class EmployeeJDBCRepo implements EmployeeReps {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from employee", Integer.class);
	}

	@Override
	public Employee findById(Long id) {
		return jdbcTemplate.queryForObject("select id, name, salary from employee where id = ?", new Object[] {id}
		, new EmployeeMapper());
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
	 return jdbcTemplate.query("select id, name, salary from employee", new EmployeeMapper());
	}

	@Override
	public int insert(Employee employee) {
		return jdbcTemplate.update("insert into employee(id,name,salary) values(?,?,?)"
				,new Object[] {employee.getId(),employee.getName(),employee.getSalary()});
	}

	@Override
	public int update(Employee employee) {
		return jdbcTemplate.update("update employee set name = ? , salary = ?"
				,new Object[] {employee.getName(),employee.getSalary()});
	}


	@Override
	public int delete(Long id) {
		return jdbcTemplate.update("delete from employee where id = ?", new Object[] {id});
	}

}
