package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Kurs;
import model.User;

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
	
	public void PrikaziSveKurseve () {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		try {
			konekcija=UspostaviKonekciju("kursevi");
			System.out.println("Konekcija je uspešno uspostavljena");
			
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
			System.err.println("Došlo je do greške prilikom pokušaja da se uspostavi veza");
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
				System.err.println("Došlo je do greške prilikom zatvaranja konekcije");
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
			System.out.println("Konekcija je uspešno uspostavljena");
			
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
			System.err.println("Došlo je do greške prilikom pokušaja da se uspostavi veza");
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
				System.err.println("Došlo je do greške prilikom zatvaranja konekcije");
			}
		}
				
	}

	public User VratiUserPoID (int ID) {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		User user=new User();
		
		try {
			konekcija=UspostaviKonekciju("kursevi");
			System.out.println("Uspešno uspostavljena konekcija");
			
			String query="SELECT * FROM users WHERE id_users=?";
			
			pst=konekcija.prepareStatement(query);
				pst.setInt(1, ID);
						
			res=pst.executeQuery();
			
			while (res.next()) {
				
				user.setID_users(res.getInt("id_users"));
				user.setUsername(res.getString("username"));
				user.setPass(res.getString("password"));
				user.setMB(res.getInt("mat_br"));
			}
			
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Došlo je do greške prilikom pokušaja da se uspostavi veza");
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
				System.err.println("Došlo je do greške prilikom zatvaranja konekcije");
			}
		}		
	}

	public List<User> VratiSveUsere() {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		List<User> listausera = new ArrayList<User>();
		
		try {
			konekcija=UspostaviKonekciju("kursevi");
			System.out.println("Uspešno uspostavljena konekcija");
			
			String query="SELECT * FROM users";
			
			pst=konekcija.prepareStatement(query);						
			res=pst.executeQuery();
			
			while (res.next()) {
			
				User user=new User();
				
				user.setID_users(res.getInt("id_users"));
				user.setUsername(res.getString("username"));
				user.setPass(res.getString("password"));
				user.setMB(res.getInt("mat_br"));
				
				listausera.add(user);
			}
			
			return listausera;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Došlo je do greške prilikom pokušaja da se uspostavi veza");
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
				System.err.println("Došlo je do greške prilikom zatvaranja konekcije");
			}
		}
	}

	public int VratiIDPoUsername (String Username) {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		int id=0;
				
		try {
			konekcija=UspostaviKonekciju("kursevi");
			String query="SELECT id_users FROM users WHERE username=?";
			pst=konekcija.prepareStatement(query);
				pst.setString(1, Username);
			res=pst.executeQuery();
			
			while (res.next()) {
				id=res.getInt("id_users");						
			}
			
			return id;
					
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Došlo je do greške prilikom pokušaja da se uspostavi veza");
			return 0;
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
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Došlo je do greške prilikom zatvaranja konekcije");
			}
		}		
	}
	
	public List<String> VratiBrojTelefona (int idUser) {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		List<String> ListaBrojevaTelefona=new ArrayList<String>();
		
		try {
			konekcija=UspostaviKonekciju("kursevi");
			String query="SELECT broj_telefona FROM brojevi_telefona WHERE user=?";
			pst=konekcija.prepareStatement(query);
				pst.setInt(1, idUser);
			res=pst.executeQuery();
			
			while (res.next()) {
				ListaBrojevaTelefona.add(res.getString("broj_telefona"));
			}
			
			return ListaBrojevaTelefona;
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Došlo je do greške prilikom pokušaja da se uspostavi veza");
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
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Došlo je do greške prilikom zatvaranja konekcije");
			}
		}				
	}
	
	public List<Integer> VratiIDKursapoIDUsera (int IDUser) {
		
		Connection konekcija=null;
		PreparedStatement pst=null;
		ResultSet res=null;
		
		List<Integer> IDKursa=new ArrayList<>();
		
		try {
			konekcija=UspostaviKonekciju("kursevi");
			String query="SELECT id_c FROM users_courses WHERE id_u=?";
			pst=konekcija.prepareStatement(query);
				pst.setInt(1, IDUser);
			res=pst.executeQuery();
			
			while (res.next()) {
				IDKursa.add(res.getInt("id_c"));
			}
			
			return IDKursa;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Došlo je do greške prilikom pokušaja da se uspostavi veza");
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
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Došlo je do greške prilikom zatvaranja konekcije");
			}
		}		
	}
}
