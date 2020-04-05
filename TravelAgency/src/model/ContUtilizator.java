package model;

import java.io.Serializable;

public class ContUtilizator implements Serializable {

	private String userName;
	private String parola;

	public ContUtilizator(String userName, String parola) {

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

}
