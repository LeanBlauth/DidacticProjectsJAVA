package application;

import java.util.Date;
import java.util.List;

import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDAO sellerDAO = DAOFactory.createSellerDAO();
		DepartmentDAO depDAO = DAOFactory.createDepartmentDAO();

		System.out.println("==== TEST 1 - seller method: findById ============");
		Seller seller1 = sellerDAO.findById(3);
		System.out.println(seller1);

		System.out.println("\n==== TEST 2 - seller method: findByDepartment ====");
		List<Seller> sellerList = sellerDAO.findByDepartment(new Department(1, null));
		for (Seller sel : sellerList) {
			System.out.println(sel);
		}

		System.out.println("\n==== TEST 3 - seller method: findAll =============");
		sellerList = sellerDAO.findAll();
		for (Seller sel : sellerList) {
			System.out.println(sel);
		}

		System.out.println("\n==== TEST 4 - seller method: insert ==============");
		Seller seller2 = new Seller(null, "Lorald McDolans", "lorald@mcdolans.com", new Date(), 3000.0,
				new Department(2, null));
		sellerDAO.insert(seller2);
		System.out.println("Seller inserted ! New ID = " + seller2.getId());

		System.out.println("\n==== TEST 5 - seller method: update ==============");
		sellerDAO.update(new Seller(9, "Rudolph", "rudy@gmail.com", new Date(), 3500.0,
				new Department(3, null)));
		System.out.println("Update completed");
		
		System.out.println("\n==== TEST 6 - seller method: delete ==============");
		sellerDAO.deleteById(12);
		System.out.println("Seller deleted");

		System.out.println("\n==== TEST 7 - department method: findById ========");
		Department dep1 = depDAO.findById(2);
		System.out.println(dep1);
		
		System.out.println("\n==== TEST 8 - department method: insert ==========");
		depDAO.insert(new Department(7,"Magic Hats"));
		System.out.println("Department inserted !");
		
		
	}
}
