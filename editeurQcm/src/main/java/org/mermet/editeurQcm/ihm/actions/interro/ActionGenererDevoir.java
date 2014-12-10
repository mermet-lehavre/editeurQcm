package org.mermet.editeurQcm.ihm.actions.interro;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;

import org.mermet.editeurQcm.ihm.EtatIhm;
import org.mermet.editeurQcm.ihm.actions.ActionPerso;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.ihm.DialogueParametresCopie;

public class ActionGenererDevoir extends ActionPerso {
	private static ActionGenererDevoir instance = null;
	private ActionGenererDevoir() {
		super("Générer les copies...");
		setEnabled(false);
	}
	public static ActionGenererDevoir getInstance() {
		if (instance == null) {
			instance = new ActionGenererDevoir();
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StructureQcm structure = EtatIhm.getInstance().getStructureQcm();
		JDialog parametres = new DialogueParametresCopie(structure);
	}
	
}
