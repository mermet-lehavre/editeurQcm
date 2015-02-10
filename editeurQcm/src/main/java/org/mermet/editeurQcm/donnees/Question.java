package org.mermet.editeurQcm.donnees;

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**                                                                                                                                                                    
 *                                                                                                                                                                     
 * @author brunomermet                                                                                                                                                 
 */
public class Question extends ObjetMetier {
    private transient String enonce;
    private final transient List<Reponse> propositions;
    public final static String ENONCE = "ENONCE";
    public final static String ADD_REPONSES = "ADD_REPONSES";
    public final static String REMOVE_REPONSES = "REMOVE_REPONSES";
    static {
        new Random(System.currentTimeMillis());
    }

    public Question(final String texte) {
        this.enonce = texte;
        propositions = new ArrayList<Reponse>();
    }

    public Question() {
        this.enonce = "";
        propositions = new ArrayList<Reponse>();
    }

    public void addReponse(final Reponse rep) {
        propositions.add(rep);
    	rep.setNumero(propositions.size());
    	support.firePropertyChange(ADD_REPONSES, null, rep);
    }
    
    public void removeReponse(final Reponse rep) {
    	propositions.remove(rep);
    	for (int i = 0; i < propositions.size();i++) {
    		propositions.get(i).setNumero(i+1);
    	}
    	support.firePropertyChange(REMOVE_REPONSES, rep, null);
    }

    public List<Reponse> getPropositions() {
    	return propositions;
    }

    public List<Reponse> getPropositionsAleatoires() {
        final List<Reponse> retour = new ArrayList<Reponse>(propositions);
        Collections.shuffle(retour);
        return retour;
    }

    public int nbReponsesJustes() {
        int retour;
        /*        retour = 0;
        for (Reponse rep : propositions) {
            if (rep.isCorrect()) {
                retour = retour + 1;
            }
        }*/
        retour = (int) propositions.stream().filter(Reponse::isCorrect).count();
        return retour;
    }

    public int nbReponsesFausses() {
        return propositions.size() - nbReponsesJustes();
    }

    public String getEnonce() {
        return enonce;
    }
    
    public void setEnonce(String texte) {
    	String ancienEnonce = enonce;
    	enonce = texte;
    	support.firePropertyChange(ENONCE, ancienEnonce, enonce);
    }
    
    public String toStringDetaille() {
        StringBuilder retour = new StringBuilder("Q = " + enonce + "\n");
        /*for (Reponse reponse : propositions) {
            retour.append("      ").append(reponse).append("\n");
        }*/
        propositions.stream().forEach(reponse ->
        	retour.append("      ")
        	.append(reponse)
        	.append('\n'));
        return retour.toString();
    }

    public String toString() {
    	if (enonce.length() > 60) {
    		return enonce.substring(0, 59) + "...";
    	}
    	else {
    		return enonce;
    	}
    }
    
    @Override
	public Element toXML() {
		Element retour = new Element("question");
		Element eltEnonce = new Element("enonce");
		eltEnonce.addContent(enonce);
		retour.addContent(eltEnonce);
		/*for (Reponse reponse : propositions) {
			retour.addContent(reponse.toXML());
		}*/
		propositions.stream().forEach(reponse ->
			retour.addContent(reponse.toXML()));
		return retour;
	}
	
	public Question(Element eltQuestion) {
		enonce = eltQuestion.getChild("enonce").getText();
		List<Element> eltReponses = eltQuestion.getChildren("reponse");
		propositions = eltReponses
				.stream()
				.map(x->new Reponse(x))
				.collect(Collectors.toList());
		for (int i = 0; i < propositions.size(); i++) {
			propositions.get(i).setNumero(i+1);
		}
		/*
		proposition = new ArrayList<>();
		for (Element eltReponse : eltReponses) {
			Reponse reponse = new Reponse(eltReponse);
			propositions.add(reponse);
		}*/
	}
}