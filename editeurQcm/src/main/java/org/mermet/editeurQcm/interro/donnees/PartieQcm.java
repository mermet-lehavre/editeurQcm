package org.mermet.editeurQcm.interro.donnees;

import java.util.List;

import org.mermet.editeurQcm.donnees.Question;

public class PartieQcm {
	private String titre;
	private List<Question> questions;

	public PartieQcm(PartieStructure structure) {
		titre = structure.getTitre();
		questions = structure.genereQuestions();
	}
	
	public String toString() {
		StringBuilder retour = new StringBuilder();
		retour.append("  partie : ").append(titre).append('\n');
		questions.stream().forEach(
				question -> {
					retour
						.append("    ")
						.append(question.getEnonce())
						.append('\n');
					question
						.getPropositionsAleatoires()
						.stream()
						.forEach(reponse ->
							retour
								.append("      ")
								.append(reponse.getProposition())
								.append('\n'));
				});
		return retour.toString();
	}
	
	public String getTitre() {
		return titre;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public int getNbQuestions() {
		return questions.size();
	}
}
