package org.mermet.editeurQcm.interro.ihm;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.donnees.Theme;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.donnees.SourceThematique;

public class DialogueCreationPartieTheme extends DialogueCreationPartie {
	private JComboBox themes;
	private Theme themeSelectionne;
	
	public DialogueCreationPartieTheme(JDialog parent, Matiere matiere, StructureQcm qcm) {
		super(parent, matiere, qcm);
	}

	@Override
	public void init() {
		themes = new JComboBox(matiere.toArray());
		JPanel panneauTheme = new JPanel();
		panneauTheme.add(themes);
		add(panneauTheme, BorderLayout.CENTER);
	}
	
	@Override
	public void majSource() {
		partie.setSource(new SourceThematique(themeSelectionne));
	}

	@Override
	public boolean validerSpecifique() {
		int numeroTheme = themes.getSelectedIndex();
		themeSelectionne = matiere.getTheme(numeroTheme);
		return themeSelectionne.getQuestions().size() >= getNbQuestions();
	}
}
