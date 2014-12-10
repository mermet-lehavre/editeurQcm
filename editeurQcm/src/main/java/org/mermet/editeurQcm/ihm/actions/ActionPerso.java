package org.mermet.editeurQcm.ihm.actions;

import javax.swing.AbstractAction;

import org.mermet.editeurQcm.ihm.fenetre.FenetrePrincipale;

public abstract class ActionPerso extends AbstractAction {
//	protected FenetrePrincipale fenetre;
	public ActionPerso(String nom) {
		super(nom);
	//	fenetre = FenetrePrincipale.getInstance();
	}
}
