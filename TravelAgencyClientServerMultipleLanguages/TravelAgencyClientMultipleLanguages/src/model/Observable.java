package model;

public interface Observable {

	public void notifyObservers();

	public void attach(Observer obs);

	public void detach(Observer obs);
}
