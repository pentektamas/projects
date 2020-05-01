package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Persistenta<T> {

	private String numeFisier;

	public void salvare(List<T> obiecte) {
		try {
			FileOutputStream file = new FileOutputStream(this.numeFisier);
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(obiecte);
			output.close();
			file.close();
		} catch (IOException ex) {
			System.out.println("Error while saving the list of objects!");
		}
	}

	public List<T> incarcare() {

		List<T> objects = new ArrayList<T>();
		try {
			FileInputStream file = new FileInputStream(this.numeFisier);
			ObjectInputStream input = new ObjectInputStream(file);
			objects = (List<T>) input.readObject();
			input.close();
			file.close();
		}

		catch (IOException ex) {
			System.out.println("Error while loading the list of objects!");
			System.out.println(ex.getMessage());
		}

		catch (ClassNotFoundException ex) {
			System.out.println("Error with class while loading the list of objects!");
		}
		return objects;
	}

	public String getNumeFisier() {
		return numeFisier;
	}

	public void setNumeFisier(String numeFisier) {
		this.numeFisier = numeFisier;
	}

}
