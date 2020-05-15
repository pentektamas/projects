package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class RBundle implements Observable {

	private ResourceBundle bundle;
	private List<Observer> views;

	public RBundle() {
		try {
			views = new ArrayList<Observer>();
			InputStream resourceInput = new FileInputStream("resources/captions_ro_RO.properties");
			bundle = new PropertyResourceBundle(resourceInput);
		} catch (IOException e) {
			System.out.println("Error with loaded language!");
		}
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : views) {
			observer.update();
		}
	}

	public void changeLanguage(Locale locale) {
		try {
			InputStream newLanguage = new FileInputStream("resources/captions_" + locale.toString() + ".properties");
			bundle = new PropertyResourceBundle(newLanguage);
			notifyObservers();
		} catch (IOException e) {
			System.out.println("Error while changing the language!");
		}
	}

	@Override
	public void attach(Observer obs) {
		views.add(obs);
	}

	@Override
	public void detach(Observer obs) {
		views.remove(obs);
	}

	public String getString(String key) {
		return bundle.getString(key);
	}

	// zero or more Object objects (or an array of them) may be passed as the
	// argument(s)
	public String getString(String key, Object... params) {
		return MessageFormat.format(bundle.getString(key), params);
	}
}
