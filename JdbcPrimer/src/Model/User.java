package Model;

public class User {
	
	private int IDUser;
	private String UserName;
	private String password;
	private int MaticniBroj;
	
	public int getIDUser() {
		return IDUser;
	}
	public void setIDUser(int iDUser) {
		IDUser = iDUser;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMaticniBroj() {
		return MaticniBroj;
	}
	public void setMaticniBroj(int maticniBroj) {
		MaticniBroj = maticniBroj;
	}
	
}
