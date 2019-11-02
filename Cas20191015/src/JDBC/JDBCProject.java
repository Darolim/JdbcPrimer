package JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Controller.MetodeJDBC;

public class JDBCProject {

	public static void main(String[] args) {
		
		Connection konekcija=null;
		Statement statement=null;
		
		try {
			
			konekcija=MetodeJDBC.UspostaviKonekciju("kursevi");
			System.out.println("Uspešno ste uspostavili vezu");
		
			String query="INSERT INTO courses VALUES (null,'JAVA Script',7500)";
			statement=konekcija.createStatement();
			statement.execute(query);
			System.out.println("Uspešno ubaèeni podaci u tabeli");
			
		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println("Došlo je do greške. Nije uspostavljena konekcija sa sistemom");
		
		}finally {
			
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				konekcija.close();
				System.out.println("Konekcija je uspešno zatvorena");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Došlo je do reške prilikom zatvaranja ");
			}
		}		
	}	
}
