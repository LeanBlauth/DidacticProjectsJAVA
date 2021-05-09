package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DBException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDAO_JDBC implements SellerDAO {
	
	private Connection conn;
	
	public SellerDAO_JDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {			
				Department dep = new Department(rs);
				Seller seller = new Seller(rs,dep);
				return seller;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name ");

			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Seller> sellerList = new ArrayList<>();
			
			Map<Integer,Department> depMap = new HashMap<>();
			Department dep = depMap.get(department.getId());
			
			while (rs.next()) {
				if (dep == null) {
					dep = new Department(rs);
					depMap.put(dep.getId(), dep);
				}
				sellerList.add(new Seller(rs,dep));
			}
			return sellerList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
