package org.mermet.editeurQcm.donnees;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jdom2.Element;

public class Theme extends ObjetMetier {
    private transient String intitule;
    private transient List<Question> questions;
    public static final String INTITULE = "INTITULE"; 
    public static final String LISTE_QUESTIONS = "LISTE_QUESTIONS";
    public Theme(final String nom) {
        intitule = nom;
        questions = new ArrayList<Question>();
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String titre) {
    	String ancienIntitule = intitule;
    	intitule = titre;
    	support.firePropertyChange(INTITULE, ancienIntitule, intitule);
    }
    
    public void addQuestion(final Question quest) {
        questions.add(quest);
    	support.firePropertyChange(LISTE_QUESTIONS, null, quest);
    }

    public void removeQuestion(final Question quest) {
        questions.remove(quest);
    	support.firePropertyChange(LISTE_QUESTIONS, quest, null);
    }

    public List<Question> getQuestions() {
    	return questions;
    }
    
    
    public String toStringComplet() {
        StringBuilder retour = new StringBuilder("---- Theme " + intitule + " ----\n");
        /*for (Question question : questions) {
            retour.append("    ").append(question).append("\n");
        }*/
        questions.stream().forEach(question ->
        	retour.append("    ")
        	.append(question)
        	.append('\n'));
        return retour.toString();
    }
    
    @Override
    public String toString() {
    	return intitule;
    }

    @Override
	public Element toXML() {
		Element retour = new Element("theme");
		Element titre = new Element("titre");
		titre.addContent(intitule);
		retour.addContent(titre);
		/*for (Question question : questions) {
			retour.addContent(question.toXML());
		}*/
		questions.stream().forEach(question -> retour.addContent(question.toXML()));
		return retour;
	}
	
	public Theme(Element eltTheme) {
		intitule = eltTheme.getChild("titre").getText();
		questions = new ArrayList<>();
		List<Element> eltQuestions = eltTheme.getChildren("question");
		questions = eltQuestions
				.stream()
				.map(x->new Question(x))
				.collect(Collectors.toList());
		/*for (Element eltQuestion : eltQuestions) {
			Question question = new Question(eltQuestion);
			questions.add(question);
		}*/
	}
}
