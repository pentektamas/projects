package model;

public class FabricaPersistenta {

	public Persistenta<?> obtinePersistenta(String tip) {
		if (tip.equals("Avion")) {
			return new PersistentaAvion();
		}
		if (tip.equals("Bilet")) {
			return new PersistentaBilet();
		}
		if (tip.equals("Calator")) {
			return new PersistentaCalator();
		}
		if (tip.equals("ContUtilizator")) {
			return new PersistentaContUtilizator();
		}
		if (tip.equals("Utilizator")) {
			return new PersistentaUtilizator();
		} else
			return null;

	}

}
