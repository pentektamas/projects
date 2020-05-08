package entity;

import java.io.Serializable;

public class Persoana implements Serializable {

	private int persID;
	private String nume;
	private int varsta;
	private String adresa;
	private String email;
	private String nrTel;

	public Persoana(int persID, String nume, int varsta, String adresa, String email, String nrTel) {
		this.persID = persID;
		this.nume = nume;
		this.varsta = varsta;
		this.adresa = adresa;
		this.email = email;
		this.nrTel = nrTel;
	}

	public int getPersID() {
		return persID;
	}

	public void setPersID(int persID) {
		this.persID = persID;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getVarsta() {
		return varsta;
	}

	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNrTel() {
		return nrTel;
	}

	public void setNrTel(String nrTel) {
		this.nrTel = nrTel;
	}
}
