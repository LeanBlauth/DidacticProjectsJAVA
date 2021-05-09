package application;

import java.util.Date;

import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		Seller seller = new Seller(7, "ronald mcdolans", "keke@kuuk.com", new Date(), 3000.0, obj);
		
		
		SellerDAO sellerDAO = DAOFactory.createSellerDAO();
		
		
		System.out.println(seller);
	}

}
