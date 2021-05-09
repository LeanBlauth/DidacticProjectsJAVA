package model.dao;

import java.sql.ResultSet;
import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDAO {
	
	void insert(Seller seller);
	void update(Seller seller);
	void deleteById(Integer id);
	Seller findById(Integer id);
	Seller instantiateSeller(ResultSet rs, Department dep);
	List<Seller> findAll();

}
