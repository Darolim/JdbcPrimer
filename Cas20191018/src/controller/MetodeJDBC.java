package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MetodeJDBC {

	public static Connection UspostaviKonekciju (String imeBaze) throws SQLException {
		
		// konekcija
		
		final String url="jdbc:mysql:///"+imeBaze;
		final String pass="root";
		final String user="root";
		return DriverManager.getConnection(url, user, pass);
				
	}
	
	public boolean UbaciuTabeluKursevi (String imeKursa, String cenaKursa) {
	
		Connection konekcija=null;
		PreparedStatement statement=null;
		
		int CenaZaUpis=Integer.parseInt(cenaKursa);
		
		try {
			konekcija=UspostaviKonekciju("kursevi");
			System.out.println("Uspešno je uspostavljena konekcija");
			
			String query="INSERT INTO courses VALUES (null,?,?)";
			statement=konekcija.prepareStatement(query);
				statement.setString(1, imeKursa);
				statement.setInt(2, CenaZaUpis);
			statement.execute();
			System.out.println("Uspešno ubaèeni podaci u tabelu");
								
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Došlo je do greške. Konekcija nije uspostavljena");
			return false;
		} finally {
			
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
				System.err.println("Došlo je do greške. Konekcija nije zatvorena");
			}
		}		
	}
	
	public boolean IzmeniCenuKursa (String imeKursa, int cenaKursa) {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		
		try {
			konekcija=UspostaviKonekciju("kursevi");
			System.out.println("Uspešno je uspostavljena konekcija");
			
			String query="UPDATE courses SET cena=? WHERE ime_kursa=?";
			pst=konekcija.prepareStatement(query);
				pst.setString(2, imeKursa);
				pst.setInt(1, cenaKursa);
				pst.executeUpdate();
			System.out.println("Uspešno je izvršena korekciaj cene");
				
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("došlo je do greške. Nije izvršena korecija cene");
			return false;
		} finally {
			try {
				pst.close();
				} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				konekcija.close();
				System.out.println("Konekcija je uspešno zatvorena");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Došlo je do greške. Konekcija nije zatvorena");
			}
		}		
	}
}
