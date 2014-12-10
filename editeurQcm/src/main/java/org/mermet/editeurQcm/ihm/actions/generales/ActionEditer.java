package org.mermet.editeurQcm.ihm.actions.generales;

import java.awt.event.ActionEvent;

import org.mermet.editeurQcm.ihm.EtatIhm;
import org.mermet.editeurQcm.ihm.actions.ActionPerso;
import org.mermet.editeurQcm.ihm.composants.DialogueEditionMatiere;

public class ActionEditer extends ActionPerso {
	private static ActionEditer instance = null;
	private ActionEditer() {
		super("Éditer");
		setEnabled(false);
	}
	
	public static ActionEditer getInstance() {
		if (instance == null) {
			instance = new ActionEditer();
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    	ActionEffacer.getInstance().setEnabled(true);
	    	ActionEnregistrer.getInstance().setEnabled(true);
	    	new DialogueEditionMatiere(EtatIhm.getInstance().getMatiere());
	}

}
