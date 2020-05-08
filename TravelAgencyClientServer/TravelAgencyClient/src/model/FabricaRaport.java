package model;

import java.util.List;

import entity.Avion;

public class FabricaRaport {

	public Raport obtineRaport(List<Avion> avioane, String tip) {
		if (tip.equals("CSV")) {
			return new RaportCSV(avioane);
		}
		if (tip.equals("JSON")) {
			return new RaportJSON(avioane);
		}
		if (tip.equals("XML")) {
			return new RaportXML(avioane);
		} else
			return null;

	}
}
