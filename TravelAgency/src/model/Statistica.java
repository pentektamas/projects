package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistica {

	private List<Bilet> bilete = new ArrayList<Bilet>();

	public Statistica(List<Bilet> listaBilete) {
		this.bilete = listaBilete;
	}

	public Map<String, Long> getStatisticaDupaPlecare() {
		Map<String, Long> valueCounts = bilete.stream().filter(b -> b.isVandut())
				.collect(Collectors.groupingBy(Bilet::getPlecare, Collectors.counting()));
		return valueCounts;
	}

	public Map<String, Long> getStatisticaDupaSosire() {
		Map<String, Long> valueCounts = bilete.stream().filter(b -> b.isVandut())
				.collect(Collectors.groupingBy(Bilet::getSosire, Collectors.counting()));
		return valueCounts;
	}

	public Map<String, Long> getStatisticaDupaPret() {
		Map<String, Long> valueCounts = bilete.stream().filter(b -> b.isVandut())
				.collect(Collectors.groupingBy(Bilet::getPretAsString, Collectors.counting()));
		return valueCounts;
	}

}
