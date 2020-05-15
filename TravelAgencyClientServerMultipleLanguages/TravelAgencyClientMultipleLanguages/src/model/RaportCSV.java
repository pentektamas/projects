package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Avion;

public class RaportCSV extends Raport {

	public RaportCSV(List<Avion> avioane) {
		super(avioane);
	}

	private List<List<String>> getPlanesStringList() {
		List<List<String>> planes = new ArrayList<List<String>>();
		for (Avion avion : getAvioane()) {
			planes.add(avion.getSaveFormat());
		}
		return planes;
	}

	@Override
	public void salvareRaport(String numeFisier) {

		try {
			FileWriter csvWriter = new FileWriter(numeFisier + ".csv");
			for (String field : getClassFields()) {
				csvWriter.append(field);
				csvWriter.append(",");
			}
			csvWriter.append("\n");

			for (List<String> plane : getPlanesStringList()) {
				csvWriter.append(String.join(",", plane));
				csvWriter.append("\n");
			}

			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}