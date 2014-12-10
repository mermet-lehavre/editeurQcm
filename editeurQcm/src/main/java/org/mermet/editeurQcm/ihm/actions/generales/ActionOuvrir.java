package org.mermet.editeurQcm.ihm.actions.generales;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.mermet.editeurQcm.LecteurMatiere;
import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.ihm.EtatIhm;
import org.mermet.editeurQcm.ihm.actions.ActionPerso;
import org.mermet.editeurQcm.ihm.actions.interro.ActionCreerInterro;
import org.mermet.editeurQcm.ihm.composants.DialogueEditionMatiere;
import org.mermet.editeurQcm.ihm.fenetre.FenetrePrincipale;

public class ActionOuvrir extends ActionPerso {
	private static ActionOuvrir instance = null;
	private ActionOuvrir() {
		super("Ouvrir");
	}
	public static ActionOuvrir getInstance() {
		if (instance == null) {
			instance = new ActionOuvrir();
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filtre = new FileNameExtensionFilter(
	        "Fichier xml", "xml");
	    chooser.setFileFilter(filtre);
	    int retour = chooser.showOpenDialog(null);
	    //int retour = chooser.showOpenDialog(FenetrePrincipale.getInstance());
	    if(retour == JFileChooser.APPROVE_OPTION) {
	    	LecteurMatiere lecteur = new LecteurMatiere(chooser.getSelectedFile());
	    	Matiere matiere = lecteur.getExamen();
	    	EtatIhm.getInstance().setMatiere(matiere);
	    	ActionEnregistrer.getInstance().setEnabled(true);
	    	ActionEditer.getInstance().setEnabled(true);
	    	ActionEffacer.getInstance().setEnabled(true);
	    	ActionCreerInterro.getInstance().setEnabled(true);
	    	DialogueEditionMatiere dialogue = new DialogueEditionMatiere(matiere);
	    }
	}

}
