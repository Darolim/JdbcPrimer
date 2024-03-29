package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Kurs;
import Model.User;

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
	
	// izmena podataka u tabeli
	
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
	
	// Dodeljivanje podataka definisanim promenljivima
	
	public void prikaziSveKurseve () {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		try {
			konekcija=uspostavikonekciju("kursevi");
			System.out.println("Konekcija je uspostavljena");
			
			String query="SELECT * FROM courses";
			pst=konekcija.prepareStatement(query);
			
			res= pst.executeQuery();
			
			while(res.next()) {
				
				int id= res.getInt("id_courses");
				String ime= res.getString("ime_kursa");
				double cena= res.getDouble("cena");
				
			System.out.println(id+" "+ime+" "+cena);	
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Došlo je do greške. Konekcija nije uspostavljena");
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
			}try {
				konekcija.close();
				System.out.println("Uspešno zatvorena konekcija");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Došlo je do greške prilikom zatvaranja konekcije");
			}
		}
		
	}
	
	public Kurs vratiKursPoId (int id) {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		Kurs kurs=new Kurs();
				
		try {
			konekcija=uspostavikonekciju("kursevi");
			System.out.println("Konekcija je uspostavljena");
			
			String query="SELECT * FROM courses WHERE id_courses = ?";
			pst=konekcija.prepareStatement(query);
				pst.setInt(1, id);
				
			res= pst.executeQuery();
			
			while(res.next()) {
				
				/*
				int idKursa= res.getInt("id_courses");
				kurs.setIdKursa(idKursa);
				String ime= res.getString("ime_kursa");
				double cena= res.getDouble("cena");
				*/
				
				kurs.setIdKursa(res.getInt("id_courses"));
				kurs.setImeKursa(res.getString("ime_kursa"));
				kurs.setCena(res.getDouble("cena"));
				
			}
			
			return kurs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Došlo je do greške. Konekcija nije uspostavljena");
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
			}try {
				konekcija.close();
				System.out.println("Uspešno zatvorena konekcija");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Došlo je do greške prilikom zatvaranja konekcije");
			}
		}		
	}

	public User VratiUseraPoId (int ID) {
				
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		User user=new User();
		
		try {
			
			konekcija=uspostavikonekciju("kursevi");
			System.out.println("Konekcija uspešno uspostavljena");
			
			String querry="SELECT * FROM users WHERE id_users =?";
			
			pst=konekcija.prepareStatement(querry);
				pst.setInt(1, ID);
			
			res=pst.executeQuery();
			
			//mapiranje klase
			
			while(res.next()) {
				user.setIDUser(res.getInt("id_users"));
				user.setUserName(res.getString("username"));
				user.setPassword(res.getString("password"));
				user.setMaticniBroj(res.getInt("mat_br"));
			}
			
			return user;
			
		} catch (SQLException e) {
	
			e.printStackTrace();
			System.err.println("Došlo je do greške. Konekcija nije uspostavljena");
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
				System.out.println("Konekcija je uspešno zatvorena");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Došlo je do greške. Konekcija nije zatvorena.");
			}
		}		
	}
}
