package org.mermet.editeurQcm.ihm.actions.generales;

import java.awt.event.ActionEvent;

import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.ihm.EtatIhm;
import org.mermet.editeurQcm.ihm.actions.ActionPerso;
import org.mermet.editeurQcm.ihm.actions.interro.ActionCreerInterro;
import org.mermet.editeurQcm.ihm.composants.DialogueEditionMatiere;

@SuppressWarnings("serial")
public class ActionNouveau extends ActionPerso {
	private static ActionNouveau instance = null;
	private ActionNouveau() {
		super("Nouveau");
	}
	public static ActionNouveau getInstance() {
		if (instance == null) {
			instance = new ActionNouveau();
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    	Matiere matiere = new Matiere("");
	    	EtatIhm.getInstance().setMatiere(matiere);
	    	ActionEnregistrer.getInstance().setEnabled(true);
	    	ActionEditer.getInstance().setEnabled(true);
	    	ActionEffacer.getInstance().setEnabled(true);
	    	ActionCreerInterro.getInstance().setEnabled(true);
	    	DialogueEditionMatiere dialogue = new DialogueEditionMatiere(matiere);
	}

}
