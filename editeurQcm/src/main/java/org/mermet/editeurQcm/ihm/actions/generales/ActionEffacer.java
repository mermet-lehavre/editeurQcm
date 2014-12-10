package org.mermet.editeurQcm.ihm.actions.generales;

import java.awt.event.ActionEvent;

import org.mermet.editeurQcm.ihm.EtatIhm;
import org.mermet.editeurQcm.ihm.actions.ActionPerso;
import org.mermet.editeurQcm.ihm.actions.interro.ActionCreerInterro;

public class ActionEffacer extends ActionPerso {
	private static ActionEffacer instance = null;
	private ActionEffacer() {
		super("Effacer");
		setEnabled(false);
	}
	
	public static ActionEffacer getInstance() {
		if (instance == null) {
			instance = new ActionEffacer();
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    	EtatIhm.getInstance().setMatiere(null);
	    	setEnabled(false);
	    	ActionEditer.getInstance().setEnabled(false);
	    	ActionEnregistrer.getInstance().setEnabled(false);
	    	ActionCreerInterro.getInstance().setEnabled(false);
	}

}
