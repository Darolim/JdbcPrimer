package JDBC;

import java.util.Scanner;

import controller.MetodeJDBC;

public class JdbcProject {

	public static void main(String[] args) {
		
		MetodeJDBC metode=new MetodeJDBC();
		
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Unesite ime kursa: ");
		String imeKursa=scanner.nextLine();
		
		System.out.println("Unesite cenu kursa: ");
		String cena=scanner.nextLine();
		
		scanner.close();
		
		metode.ubaciUtabeluKursevi(imeKursa, cena);
	}
}
