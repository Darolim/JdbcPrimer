package JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import controller.MetodeJDBC;

public class JdbcProject {

	public static void main(String[] args) {
		
		Connection konekcija=null;
		Statement statement=null;
		
		try {
			konekcija=MetodeJDBC.uspostavikonekciju("kursevi");
			System.out.println("Uspostavili ste konekciju sa bazom");
			
			String query="INSERT INTO courses VALUES(null,'c#',10000)";
			statement=konekcija.createStatement();
			statement.execute(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Došlo je do greške. Nije uspostavljena konekcija sa bazom");;
		}
	}
}
