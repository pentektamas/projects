package model;

import java.util.List;

import entity.Bilet;

public class ConstructorStatistica {

	public Statistica creareStatistica(List<Bilet> lista) {

		return new Statistica(lista);
	}

}
