import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url="jdbc:mysql:///world";
		String user="root";
		String password="root";
		Connection konekcija=null;
		
		try {
			
			konekcija=DriverManager.getConnection(url, user, password);
			System.out.println("Konekcija je uspešno uspostavljena!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
			System.err.println("Konekcija nije uspostavljena!");
		
		}finally {
			
			if (konekcija!=null) {
			
			try {
				
				konekcija.close();
				System.out.println("Konekcija je uspešno zatvorena!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				
			}
			}
		}
	}
}
