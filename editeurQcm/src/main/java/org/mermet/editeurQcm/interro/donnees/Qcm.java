package org.mermet.editeurQcm.interro.donnees;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Qcm implements IQcm {
	private StructureQcm structure;
	private List<PartieQcm> parties; 
	
	public Qcm(StructureQcm maStructure) {
		structure = maStructure;
		parties = new ArrayList<>();
		generer();
	}
	
	private final void generer() {
		parties = structure.
				getParties()
				.stream()
				.map(partie -> new PartieQcm(partie))
				.collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		StringBuilder retour = new StringBuilder();
		retour.append("--------------------------------------").append('\n');
		retour.append("QCM : " + structure.getTitre());
		parties
			.stream()
			.forEach(partie -> retour.append(partie.toString()));
		retour.append("--------------------------------------").append('\n');
		return retour.toString();
	}

	@Override
	public String getTitre() {
		return structure.getTitre();
	}
	
	@Override
	public List<PartieQcm> getListeParties() {
		return parties;
	}
}
