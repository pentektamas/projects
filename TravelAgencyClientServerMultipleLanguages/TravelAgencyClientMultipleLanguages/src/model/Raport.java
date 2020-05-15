package model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import entity.Avion;

public abstract class Raport {

	private List<Avion> avioane = new ArrayList<Avion>();

	public Raport(List<Avion> avioane) {
		this.avioane = avioane;
	}

	public List<String> getClassFields() {
		List<Field> fields = Arrays.asList(Avion.class.getDeclaredFields());
		return fields.stream().map(f -> f.getName()).collect(Collectors.toList());
	}

	public abstract void salvareRaport(String numeFisier);

	public List<Avion> getAvioane() {
		return avioane;
	}

	public void setAvioane(List<Avion> avioane) {
		this.avioane = avioane;
	}

}
