package model;

public class PersistentaAvion extends Persistenta<Avion> {

	public PersistentaAvion() {

		String className = this.getClass().getSimpleName();
		String fileName = className.substring(11, className.length()) + ".ser";
		setNumeFisier(fileName);
	}

}
