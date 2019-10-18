package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MetodeJDBC {

	
	// konekcija
	
	private static Connection uspostavikonekciju(String imeBaze) throws SQLException {
		
		final String url="jdbc:mysql:///"+imeBaze;
		final String pass="root";
		final String user="root";
		
		return DriverManager.getConnection(url, user, pass);
		
	}
	
	// unos u tabele
	
	public boolean ubaciUtabeluKursevi(String imeKursa, String cena) {
		
		Connection konekcija=null;
		PreparedStatement statement=null;
		
		int cenaZaUpis=Integer.parseInt(cena);
		
		try {
			konekcija = uspostavikonekciju("kursevi");
			System.out.println("Konekcija je uspostavljena");
			
			String query="INSERT INTO courses VALUES (null,?,?)";
			statement=konekcija.prepareStatement(query);
				statement.setString(1, imeKursa);
				statement.setInt(2, cenaZaUpis);
			statement.execute();			
			
			System.out.println("Uspešno ubačen kurs");
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Konekcija nije uspostavljena");
			return false;
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				konekcija.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public boolean izmeniCenuKursa(String ime_Kursa, int cena) {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		
		try {
			konekcija=uspostavikonekciju("kursevi");
			System.out.println("Konekcija uspostavljena");
			String query="UPDATE courses SET cena=? WHERE ime_Kursa=?";
			pst=konekcija.prepareStatement(query);
				pst.setInt(1, cena);
				pst.setString(2, ime_Kursa);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				konekcija.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}
