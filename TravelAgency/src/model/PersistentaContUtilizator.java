package model;

public class PersistentaContUtilizator extends Persistenta<ContUtilizator> {

	public PersistentaContUtilizator() {

		String className = this.getClass().getSimpleName();
		String fileName = className.substring(11, className.length()) + ".ser";
		setNumeFisier(fileName);
	}

}
