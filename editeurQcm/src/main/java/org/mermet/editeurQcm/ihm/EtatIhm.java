package org.mermet.editeurQcm.ihm;

import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;

public class EtatIhm {
	private Matiere matiere;
	private StructureQcm structureQcm;
	private static EtatIhm instance = null;
	
	private EtatIhm() {}

	public static EtatIhm getInstance() {
		if (instance == null) {
			instance = new EtatIhm();
		}
		return  instance;
	}
	
	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public StructureQcm getStructureQcm() {
		return structureQcm;
	}

	public void setStructureQcm(StructureQcm structureQcm) {
		this.structureQcm = structureQcm;
	}
}
