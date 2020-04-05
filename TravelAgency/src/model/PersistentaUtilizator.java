package model;

public class PersistentaUtilizator extends Persistenta<Utilizator> {

	public PersistentaUtilizator() {

		String className = this.getClass().getSimpleName();
		String fileName = className.substring(11, className.length()) + ".ser";
		setNumeFisier(fileName);
	}


}
