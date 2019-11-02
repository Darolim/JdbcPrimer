package JDBC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.MetodeJDBC;
import model.Kurs;
import model.User;

public class JDBCProject {
	
	public static void main(String[] args) {
		
		MetodeJDBC metode=new MetodeJDBC();
		
		Scanner ucitaj=new Scanner(System.in);
		
		System.out.println("Unesite Usera: ");
		/*String UserName=ucitaj.nextLine();
		
		int id=metode.VratiIDPoUsername(UserName);
		
		List<String> ListaBrojevaTelefona=new ArrayList<String>();
		
		if (id!=0) {
			ListaBrojevaTelefona=metode.VratiBrojTelefona(id);
			for (String lbt : ListaBrojevaTelefona) {
				
				System.out.println("Broj telefona za zadati user "+UserName+" je "+lbt);
				
			}
			}else {
				System.out.println("Nisam pronašao usera");
			}
			*/
		
		List<Integer> ListaIDKursa = new ArrayList<>();
		
		int id=ucitaj.nextInt();
		
		ListaIDKursa=metode.VratiIDKursapoIDUsera(id);
		
		for (Integer k : ListaIDKursa) {
			
			Kurs kurs=metode.VratiKursPoID(id);
			System.out.println("Kurs: "+kurs.getImeKursa()+", Cena kursa: "+kurs.getCenaKursa());
		}
	}		
}
