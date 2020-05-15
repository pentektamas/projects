package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import entity.Avion;

@XmlRootElement
public class AvionList {

	private List<Avion> avioane;

	public AvionList() {
		avioane = new ArrayList<Avion>();
	}

	public AvionList(List<Avion> avioane) {
		this.avioane = avioane;
	}

	public List<Avion> getAvioane() {
		return avioane;
	}

	public void setAvioane(List<Avion> avioane) {
		this.avioane = avioane;
	}

}
