package model;

import java.io.Serializable;

public class Persoana implements Serializable {

	private String nume;
	private int varsta;
	private String adresa;
	private String email;
	private String nrTel;

	public Persoana(String nume, int varsta, String adresa, String email, String nrTel) {
		this.nume = nume;
		this.varsta = varsta;
		this.adresa = adresa;
		this.email = email;
		this.nrTel = nrTel;
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

	public void Setvarsta(int varsta) {
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
