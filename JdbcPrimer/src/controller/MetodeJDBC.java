package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetodeJDBC {

	public static Connection uspostavikonekciju(String imeBaze) throws SQLException {
		
		final String url="jdbc:mysql:///"+imeBaze;
		final String pass="root";
		final String user="root";
		
		return DriverManager.getConnection(url, user, pass);
		
	}
}
