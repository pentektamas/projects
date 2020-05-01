package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Calator extends Persoana implements Serializable  {

	private List<Bilet> bilete;
	private String contBancar;

	public Calator(String nume, int varsta, String adresa, String email, String nrTel, String contBancar) {
		super(nume, varsta, adresa, email, nrTel);
		this.contBancar = contBancar;
		this.bilete = new ArrayList<Bilet>();
	}

	public Calator(Persoana persoana, String contBancar) {
		super(persoana.getNume(), persoana.getVarsta(), persoana.getAdresa(), persoana.getEmail(), persoana.getNrTel());
		this.contBancar = contBancar;
	}

	public void addBilet(Bilet bilet) {
		bilete.add(bilet);
	}

	public void deleteBilet(Bilet bilet) {
		bilete.remove(bilet);
	}

	public List<Bilet> getBilete() {
		return bilete;
	}

	public void setBilete(List<Bilet> bilete) {
		this.bilete = bilete;
	}

	public String getContBancar() {
		return contBancar;
	}

	public void setContBancar(String contBancar) {
		this.contBancar = contBancar;
	}

}
