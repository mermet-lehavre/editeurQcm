package org.mermet.editeurQcm.ihm.actions.generales;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.mermet.editeurQcm.EcrivainMatiere;
import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.ihm.EtatIhm;
import org.mermet.editeurQcm.ihm.actions.ActionPerso;
import org.mermet.editeurQcm.ihm.fenetre.FenetrePrincipale;

public class ActionEnregistrer extends ActionPerso {
	private static ActionEnregistrer instance = null;
	
	private ActionEnregistrer() {
		super("Enregistrer");
		setEnabled(false);
	}
	
	public static ActionEnregistrer getInstance() {
		if (instance == null) {
			instance = new ActionEnregistrer();
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filtre = new FileNameExtensionFilter(
	        "Fichier xml", "xml");
	    chooser.setFileFilter(filtre);
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int retour = chooser.showSaveDialog(null);
	    //int retour = chooser.showSaveDialog(FenetrePrincipale.getInstance());
	    if(retour == JFileChooser.APPROVE_OPTION) {
	    	Matiere matiere = EtatIhm.getInstance().getMatiere();
	    	File fichier = new File(chooser.getSelectedFile(),matiere.getTitre()+".xml");
	    	EcrivainMatiere ecrivain = new EcrivainMatiere(fichier);
	    	ecrivain.ecrire(matiere);
	    }
	}

}
