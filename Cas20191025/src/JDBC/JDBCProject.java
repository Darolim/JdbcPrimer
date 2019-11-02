package JDBC;

import java.util.List;
import controller.MetodeJDBC;
import model.User;

public class JDBCProject {
	
	public static void main(String[] args) {
		
		MetodeJDBC metode=new MetodeJDBC();
		
		// metode.PrikaziSveKurseve();
		
		/*
		 
		Scanner ucitaj=new Scanner(System.in);
		
		System.out.println("Unesite ID korisnika: ");
		
		String ID=ucitaj.nextLine();
		ucitaj.close();
		
		int IDUsera=Integer.parseInt(ID);
		
		User u=metode.VratiUserPoID(IDUsera);
				
		if (u.getID_users()==0) {
			
			System.out.println("Sistem nije pronašao traženi ID");
			
		} else {
		System.out.println("ID: "+u.getID_users());
		System.out.println("Ime kursa: "+u.getUsername());
		System.out.println("Password: "+u.getPass());
		System.out.println("Matièni broj: "+u.getMB());
		}
	
	*/
	
		List<User> lstUsera=metode.VratiSveUsere();
		
		for (User u : lstUsera) {
			
			System.out.println("ID: "+u.getID_users());
			System.out.println("Ime: "+u.getUsername());
			System.out.println("Password: "+u.getPass());
			System.out.println("MB: "+u.getMB());
			
		}	
	}
}
