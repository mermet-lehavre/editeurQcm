package org.mermet.editeurQcm.interro.donnees;

import org.mermet.editeurQcm.donnees.Question;

import java.util.List;

public class PartieStructure {
	private String titre;
	private int nbQuestions;
	private Source source;
	
	public PartieStructure(String monTitre, int taille) {
		titre = monTitre;
		nbQuestions = taille;
	}
	public void setSource(Source maSource) {
		source = maSource;
	}
	public void setNbQuestions(int n) {
		nbQuestions = n;
	}
	
	public void setTitre(String monTitre) {
		titre = monTitre;
	}
	
	public String getTitre() {
		return titre;
	}

	public int getNbQuestions() {
		return nbQuestions;
	}
	
	public String getResume() {
		StringBuilder retour = new StringBuilder();
		retour.append("Partie ").append(titre).append(':');
		retour.append(nbQuestions).append(" question").append(nbQuestions > 1 ? "s":"");
		retour.append(". source = ");
		retour.append(source.getResume());
		retour.append('.');
		return retour.toString();
	}
	public List<Question> genereQuestions() {
		return source.genereQuestions(nbQuestions);
	}
}
