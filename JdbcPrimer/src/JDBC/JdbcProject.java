package JDBC;

import java.util.Scanner;

import Model.Kurs;
import controller.MetodeJDBC;

public class JdbcProject {

	public static void main(String[] args) {
		
		MetodeJDBC metode = new MetodeJDBC();
		
		Kurs k = metode.vratiKursPoId(3);
		
		System.out.println("Id: " + k.getIdKursa());
		System.out.println("Ime: " + k.getImeKursa());
		System.out.println("Cena: " + k.getCena());
}
}
