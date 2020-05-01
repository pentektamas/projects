package model;

public class PersistentaCalator extends Persistenta<Calator> {

	public PersistentaCalator() {

		String className = this.getClass().getSimpleName();
		String fileName = className.substring(11, className.length()) + ".ser";
		setNumeFisier(fileName);
	}

}
