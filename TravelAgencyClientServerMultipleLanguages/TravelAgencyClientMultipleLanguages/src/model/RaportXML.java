package model;

import java.io.FileWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import entity.Avion;

public class RaportXML extends Raport {

	public RaportXML(List<Avion> avioane) {
		super(avioane);
	}

	@Override
	public void salvareRaport(String numeFisier) {
		try {

			FileWriter xmlWriter = new FileWriter(numeFisier + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(AvionList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			AvionList avioane = new AvionList(getAvioane());

			jaxbMarshaller.marshal(avioane, xmlWriter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
