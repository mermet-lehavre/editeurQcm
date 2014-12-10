package org.mermet.editeurQcm.donnees;

import java.beans.PropertyChangeListener;

import org.jdom2.Element;

/**                                                                                                                                                                    
 *                                                                                                                                                                     
 * @author brunomermet                                                                                                                                                 
 */
public class Reponse extends ObjetMetier {
	public static final String PROPOSITION = "PROPOSITION";
	public static final String CORRECT = "CORRECT";
	public static final String NUMERO = "NUMERO";
    private String proposition;
    private transient boolean correct;
    private int numero;
    public Reponse(final String maProposition, final boolean juste) {
        this.proposition = maProposition;
        this.correct = juste;
        numero = -1;
    }

    void setNumero(int n) {
    	int ancienNumero = numero;
    	numero = n;
    	support.firePropertyChange(NUMERO,ancienNumero, numero);
    }
    
    public int getNumero() {
    	return numero;
    }
    
    public String getProposition() {
        return proposition;
    }

    public void setProposition(final String maProposition) {
    	String ancienneProposition = proposition;
        this.proposition = maProposition;
        support.firePropertyChange(PROPOSITION,ancienneProposition,maProposition);
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(final boolean juste) {
    boolean ancienneValeur = correct;
	this.correct = juste;
    support.firePropertyChange(CORRECT,ancienneValeur,juste);
    }

    @Override
    public String toString() {
	return "Reponse{" + "proposition=" + proposition
		+ ", correct=" + correct + '}';
    }

    @Override
	public Element toXML() {
		Element retour = new Element("reponse");
		Element eltProposition = new Element("proposition");
		eltProposition.addContent(proposition);
		retour.addContent(eltProposition);
		Element eltCorrect = new Element("correct");
		eltCorrect.addContent(correct?"true":"false");
		retour.addContent(eltCorrect);
		return retour;
	}

	public Reponse(Element eltReponse) {
		proposition = eltReponse.getChild("proposition").getText();
        correct = Boolean.parseBoolean(eltReponse.getChild("correct").getText());
	}
	
}