package JDBC;

import java.util.Scanner;

import controller.MetodeJDBC;
import model.Kurs;

public class JDBCProject {
	
	public static void main(String[] args) {
		
		MetodeJDBC metode=new MetodeJDBC();
		
		// metode.PrikaziSveKurseve();
		
		Scanner ucitaj=new Scanner(System.in);
		
		System.out.println("Unesite ID kursa: ");
		
		String ID=ucitaj.nextLine();
		ucitaj.close();
		
		int IDKursa=Integer.parseInt(ID);
		
		Kurs k=metode.VratiKursPoID(IDKursa);
		
		
		if (k.getIDKursa()==0) {
			
			System.out.println("Sistem nije pronašao traženi ID");
			
		} else {
		System.out.println("ID: "+k.getIDKursa());
		System.out.println("Ime kursa: "+k.getImeKursa());
		System.out.println("Cena kursa: "+k.getCenaKursa());
		}
	}

}
