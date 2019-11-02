package JDBC;

import java.sql.Connection;
import java.util.Scanner;

import controller.MetodeJDBC;

public class JDBCProject {
	
	public static void main(String[] args) {
		
		MetodeJDBC metode=new MetodeJDBC();
		
		Scanner ucitaj=new Scanner(System.in);
		/*
		System.out.println("Unesite ime kursa: ");
		String imeKursa=ucitaj.nextLine();
		
		System.out.println("Unesi cenu kursa: ");
		String cenaKursa=ucitaj.nextLine();
				
		ucitaj.close();
		
		metode.UbaciuTabeluKursevi(imeKursa, cenaKursa);
		*/
		
		System.out.println("Unesite ime kursa: ");
		String imeKursa=ucitaj.nextLine();
		
		System.out.println("Unesi cenu kursa: ");
		int cenaKursa=ucitaj.nextInt();
		
		ucitaj.close();
		
		metode.IzmeniCenuKursa(imeKursa, cenaKursa);
	}

}
