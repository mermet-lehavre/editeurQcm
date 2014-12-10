package org.mermet.editeurQcm.interro.donnees;

import java.util.List;

import org.mermet.editeurQcm.donnees.Question;

public interface Source {
	List<Question> getQuestions(int nb);

	public String getResume();

	List<Question> genereQuestions(int nb);
}
