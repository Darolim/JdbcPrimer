package JDBC;

import java.util.Scanner;

import Model.Kurs;
import Model.User;
import controller.MetodeJDBC;

public class JdbcProject {

	public static void main(String[] args) {
		
		MetodeJDBC metode = new MetodeJDBC();
		
		Scanner ucitaj=new Scanner(System.in);
		System.out.println("Unesite ID");
		String ID=ucitaj.nextLine();
				
		User user = metode.VratiUseraPoId(Integer.parseInt(ID));
		
		if(user.getIDUser() !=0) {
		System.out.println("ID: "+user.getIDUser());
		System.out.println("Ime i prezime: "+user.getUserName());
		System.out.println("Password: "+user.getPassword());
		System.out.println("Matični broj: "+user.getMaticniBroj());
		
}else {
	System.out.println("Sistem nije pronašao traženi ID");
}
}
}
