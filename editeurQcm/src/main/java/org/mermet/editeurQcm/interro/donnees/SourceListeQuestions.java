package org.mermet.editeurQcm.interro.donnees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mermet.editeurQcm.donnees.Question;

public class SourceListeQuestions implements Source {
	private List<Question> questionsSource;

	public SourceListeQuestions() {
		questionsSource = new ArrayList<>();
	}
	
	public void addQuestion(Question question) {
		questionsSource.add(question);
	}
	
	public void removeQuestion(Question question) {
		questionsSource.remove(question);
	}
	
	@Override
	public List<Question> getQuestions(int nb) {
		return null;
	}

	@Override
	public String getResume() {
		int nbQuestions = questionsSource.size();
		StringBuilder retour = new StringBuilder();
		retour.append(nbQuestions).append(" question");
		retour.append(nbQuestions > 1 ? "s" : "");
		return retour.toString();
	}
	
	@Override
	public List<Question> genereQuestions(int nb) {
		List<Question> pioche = new ArrayList<>(questionsSource);
		Collections.shuffle(pioche);
		pioche = pioche.stream().limit(nb).collect(Collectors.toList());
		return pioche;
	}

}
