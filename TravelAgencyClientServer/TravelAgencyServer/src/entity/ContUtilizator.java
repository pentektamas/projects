package entity;

import java.io.Serializable;

public class ContUtilizator implements Serializable {

	private int contID;
	private String userName;
	private String parola;

	public ContUtilizator(int contID, String userName, String parola) {

		this.contID = contID;
		this.userName = userName;
		this.parola = parola;
	}

	public boolean verifyCredentials(String user, String password) {
		return this.userName.equals(user) && this.parola.equals(password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public int getContID() {
		return contID;
	}

	public void setContID(int contID) {
		this.contID = contID;
	}

}
