package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;

public class RaportJSON extends Raport {

	public RaportJSON(List<Avion> avioane) {
		super(avioane);
	}

	@Override
	public void salvareRaport(String numeFisier) {

		List<String> fields = getClassFields();

		try {
			FileWriter jsonWriter = new FileWriter(numeFisier + ".json");

			for (Avion avion : getAvioane()) {
				List<String> planes = avion.getSaveFormat();
				JSONObject planeJSON = new JSONObject();
				for (int i = 0; i < planes.size(); i++) {
					planeJSON.put(fields.get(i), planes.get(i));
				}
				jsonWriter.append(planeJSON.toJSONString());
				jsonWriter.append("\n\n\n");
			}

			jsonWriter.flush();
			jsonWriter.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
