package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Kurs;

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
			System.out.println("Uspe�no je uspostavljena konekcija");
			
			String query="INSERT INTO courses VALUES (null,?,?)";
			statement=konekcija.prepareStatement(query);
				statement.setString(1, imeKursa);
				statement.setInt(2, CenaZaUpis);
			statement.execute();
			System.out.println("Uspe�no uba�eni podaci u tabelu");
								
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Do�lo je do gre�ke. Konekcija nije uspostavljena");
			return false;
		} finally {
			
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				konekcija.close();
				System.out.println("Konekcija je uspe�no zatvorena");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Do�lo je do gre�ke. Konekcija nije zatvorena");
			}
		}		
	}
	
	public boolean IzmeniCenuKursa (String imeKursa, int cenaKursa) {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		
		try {
			konekcija=UspostaviKonekciju("kursevi");
			System.out.println("Uspe�no je uspostavljena konekcija");
			
			String query="UPDATE courses SET cena=? WHERE ime_kursa=?";
			pst=konekcija.prepareStatement(query);
				pst.setString(2, imeKursa);
				pst.setInt(1, cenaKursa);
				pst.executeUpdate();
			System.out.println("Uspe�no je izvr�ena korekciaj cene");
				
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("do�lo je do gre�ke. Nije izvr�ena korecija cene");
			return false;
		} finally {
			try {
				pst.close();
				} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				konekcija.close();
				System.out.println("Konekcija je uspe�no zatvorena");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Do�lo je do gre�ke. Konekcija nije zatvorena");
			}
		}		
	}
	
	public void PrikaziSveKurseve () {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		try {
			konekcija=UspostaviKonekciju("kursevi");
			System.out.println("Konekcija je uspe�no uspostavljena");
			
			String query="SELECT * FROM courses";
			pst=konekcija.prepareStatement(query);
			res=pst.executeQuery();
			
			while (res.next()) {
				int id=res.getInt("id_courses");
				String imeKursa=res.getString("ime_kursa");
				int cenaKursa=res.getInt("cena");
				
				System.out.println("ID: "+id+" Ime kursa: "+imeKursa+" Cena kursa: "+cenaKursa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Do�lo je do gre�ke prilikom poku�aja da se uspostavi veza");
		} finally {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				konekcija.close();
				System.out.println("Konekcija je uspe�no zatvorena");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Do�lo je do gre�ke prilikom zatvaranja konekcije");
			}
		}
		
	}

	public Kurs VratiKursPoID (int ID) {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		
		Kurs kurs=new Kurs();
		
		try {
			konekcija=UspostaviKonekciju("kursevi");
			System.out.println("Konekcija je uspe�no uspostavljena");
			
			String query="SELECT * FROM courses WHERE id_courses=?";
			pst=konekcija.prepareStatement(query);
				pst.setInt(1, ID);
			
			res=pst.executeQuery();
			
			while (res.next()) {
				
				kurs.setIDKursa(res.getInt("id_courses"));
				kurs.setImeKursa(res.getString("ime_kursa"));
				kurs.setCenaKursa(res.getInt("cena"));
				
			}
			
			return kurs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Do�lo je do gre�ke prilikom poku�aja da se uspostavi veza");
			return null;
		} finally {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				konekcija.close();
				System.out.println("Konekcija je uspe�no zatvorena");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Do�lo je do gre�ke prilikom zatvaranja konekcije");
			}
		}
				
	}

}
