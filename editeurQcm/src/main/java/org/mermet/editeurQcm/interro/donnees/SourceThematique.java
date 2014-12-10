package org.mermet.editeurQcm.interro.donnees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Theme;

public class SourceThematique implements Source {
	private Theme theme;

	public SourceThematique(Theme monTheme) {
		theme = monTheme;
	}

	@Override
	public List<Question> getQuestions(int nb) {
		return null;
	}

	@Override
	public String getResume() {
		return "Thème " + theme.getIntitule();
	}

	@Override
	public List<Question> genereQuestions(int nb) {
		List<Question> pioche = new ArrayList<>(theme.getQuestions());
		Collections.shuffle(pioche);
		/*while (pioche.size() > nb) {
			pioche.remove(0);
		}*/
		pioche = pioche.stream().limit(nb).collect(Collectors.toList());
		return pioche;
	}

}
