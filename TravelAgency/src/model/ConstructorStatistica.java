package model;

import java.util.List;

public class ConstructorStatistica {

	public Statistica creareStatistica(List<Bilet> lista) {

		return new Statistica(lista);
	}

}
