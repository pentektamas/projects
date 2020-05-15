package entity;

import java.io.Serializable;

public class Bilet implements Serializable {

	private int nrBilet;
	private String plecare;
	private String sosire;
	private String data;
	private int pret;
	private boolean isVandut;

	public Bilet(int nrBilet, String plecare, String sosire, String data, int pret, boolean isVandut) {
		this.nrBilet = nrBilet;
		this.plecare = plecare;
		this.sosire = sosire;
		this.data = data;
		this.pret = pret;
		this.isVandut = isVandut;
	}

	public int getNrBilet() {
		return nrBilet;
	}

	public void setNrBilet(int nrBilet) {
		this.nrBilet = nrBilet;
	}

	public String getPlecare() {
		return plecare;
	}

	public void setPlecare(String plecare) {
		this.plecare = plecare;
	}

	public String getSosire() {
		return sosire;
	}

	public void setSosire(String sosire) {
		this.sosire = sosire;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getPret() {
		return pret;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}

	public boolean isVandut() {
		return isVandut;
	}

	public void setVandut(boolean isVandut) {
		this.isVandut = isVandut;
	}

	public String getPretAsString() {
		return String.valueOf(this.getPret());
	}

	public String toString() {
		return "Ticket number: " + this.nrBilet + "\nDeparture: " + this.plecare + "\nArrival: " + this.sosire
				+ "\nDate: " + this.data + "\nPrice: " + this.pret + " RON" + "\nIsSold: " + this.isVandut;
	}

}
