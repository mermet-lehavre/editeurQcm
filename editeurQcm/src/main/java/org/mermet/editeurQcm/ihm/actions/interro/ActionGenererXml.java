package org.mermet.editeurQcm.ihm.actions.interro;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;

import org.mermet.editeurQcm.ihm.EtatIhm;
import org.mermet.editeurQcm.ihm.actions.ActionPerso;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.ihm.DialogueParametresXml;

public class ActionGenererXml extends ActionPerso {
	private static ActionGenererXml instance = null;
	private ActionGenererXml() {
		super("Générer le fichier Xml");
		setEnabled(false);
	}
	public static ActionGenererXml getInstance() {
		if (instance == null) {
			instance = new ActionGenererXml();
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StructureQcm structure = EtatIhm.getInstance().getStructureQcm();
		JDialog parametres = new DialogueParametresXml(structure);
	}

}
