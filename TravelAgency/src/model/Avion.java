package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Avion implements Serializable {

	private int nrAvion;
	private int nrLocuri;
	private String tip;
	private String liniaAeriana;
	private int durataZborului;
	private String plecare;
	private String sosire;
	private int pretBilet;

	public Avion(int nrAvion, int nrLocuri, String tip, String liniaAeriana, int durataZborului, String plecare,
			String sosire, int pret) {
		super();
		this.nrAvion = nrAvion;
		this.nrLocuri = nrLocuri;
		this.tip = tip;
		this.liniaAeriana = liniaAeriana;
		this.durataZborului = durataZborului;
		this.plecare = plecare;
		this.sosire = sosire;
		this.pretBilet = pret;
	}

	public int getNrAvion() {
		return nrAvion;
	}

	public void setNrAvion(int nrAvion) {
		this.nrAvion = nrAvion;
	}

	public int getNrLocuri() {
		return nrLocuri;
	}

	public void setNrLocuri(int nrLocuri) {
		this.nrLocuri = nrLocuri;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getLiniaAeriana() {
		return liniaAeriana;
	}

	public void setLiniaAeriana(String liniaAeriana) {
		this.liniaAeriana = liniaAeriana;
	}

	public int getDurataZborului() {
		return durataZborului;
	}

	public void setDurataZborului(int durataZborului) {
		this.durataZborului = durataZborului;
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

	public int getPretBilet() {
		return pretBilet;
	}

	public void setPretBilet(int pretBilet) {
		this.pretBilet = pretBilet;
	}

	public List<String> getSaveFormat() {
		return Arrays.asList(String.valueOf(this.nrAvion), String.valueOf(this.nrLocuri), this.tip, this.liniaAeriana,
				String.valueOf(this.durataZborului), this.plecare, this.sosire, String.valueOf(this.pretBilet));
	}

	public String toString() {
		return "Airline: " + this.liniaAeriana + "\nAirplane number: " + this.nrAvion + "\nType: " + this.tip
				+ "\nDeparture: " + this.plecare + "\nArrival: " + this.sosire + "\nTicket price: " + this.pretBilet
				+ " RON" + "\nNumber of free seats: " + this.nrLocuri + "\nFlight Duration: " + this.durataZborului;
	}

}
