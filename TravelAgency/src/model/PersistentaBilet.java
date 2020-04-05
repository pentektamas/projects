package model;

public class PersistentaBilet extends Persistenta<Bilet> {

	public PersistentaBilet() {

		String className = this.getClass().getSimpleName();
		String fileName = className.substring(11, className.length()) + ".ser";
		setNumeFisier(fileName);
	}

}
