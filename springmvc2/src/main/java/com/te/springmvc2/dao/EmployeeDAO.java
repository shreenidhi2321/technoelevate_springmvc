package com.te.springmvc2.dao;

import java.util.List;

import com.te.springmvc2.bean.EmployeeBean;

public interface EmployeeDAO {
	
	public EmployeeBean authenticate(int id, String password);
	
	public EmployeeBean getEmployee(int id);
	
	public boolean deleteEmp(int id);
	
	public List<EmployeeBean> getAllEmployees();

}
