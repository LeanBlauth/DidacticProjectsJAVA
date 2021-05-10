package application;

import java.util.List;

import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDAO sellerDAO = DAOFactory.createSellerDAO();
		
		System.out.println("==== TEST 1 - seller method: findById ============");
		Seller seller = sellerDAO.findById(3);
		System.out.println(seller);
		
		System.out.println("\n==== TEST 2 - seller method: findByDepartment ====");
		List<Seller> sellerList = sellerDAO.findByDepartment(new Department(1,null));
		for (Seller sel : sellerList) {
			System.out.println(sel);
		}
		
		System.out.println("\n==== TEST 3 - seller method: findAll =============");
		sellerList = sellerDAO.findAll();
		for (Seller sel : sellerList) {
			System.out.println(sel);
		}
	}
}
