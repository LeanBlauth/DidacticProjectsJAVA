package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.DepartmentDAO;
import model.entities.Department;

public class DepartmentDAO_JDBC implements DepartmentDAO {

	public DepartmentDAO_JDBC(Connection connection) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Department dep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department dep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
