package entity;

import java.io.Serializable;

public class Utilizator extends Persoana implements Serializable {

	private String tip;
	private ContUtilizator contUtilizator;

	public Utilizator(int persID, String nume, int varsta, String adresa, String email, String nrTel, String tip,
			ContUtilizator contUtilizator) {
		super(persID, nume, varsta, adresa, email, nrTel);
		this.tip = tip;
		this.setContUtilizator(contUtilizator);
	}

	public Utilizator(Persoana persoana, String tip) {
		super(persoana.getPersID(), persoana.getNume(), persoana.getVarsta(), persoana.getAdresa(), persoana.getEmail(),
				persoana.getNrTel());
		this.tip = tip;
	}

	public boolean verifyCont(String userName, String password, String type) {
		boolean credentials = contUtilizator.verifyCredentials(userName, password);
		boolean correctType = this.tip.equals(type);
		return credentials && correctType;

	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public ContUtilizator getContUtilizator() {
		return contUtilizator;
	}

	public void setContUtilizator(ContUtilizator contUtilizator) {
		this.contUtilizator = contUtilizator;
	}

	public String toString() {
		return "Name: " + getNume() + "\nAge: " + getVarsta() + "\nUsername: " + this.contUtilizator.getUserName()
				+ "\nPassword: " + this.contUtilizator.getParola() + "\nPhone number:" + getNrTel();
	}

}
