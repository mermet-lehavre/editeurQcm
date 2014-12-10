package org.mermet.editeurQcm.ihm.composants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Frame;

import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.donnees.Theme;
import org.mermet.editeurQcm.ihm.EtatIhm;

public class DialogueEditionMatiere extends JDialog {
	private Matiere modeleMatiere;
	private JTextArea nomMatiere;
	private JPanel listeTheme;
	private JButton ajouter;
	
	public DialogueEditionMatiere(Matiere matiere) {
		super((Frame)null,matiere.getTitre(),true);
		//super(FenetrePrincipale.getInstance(),matiere.getTitre(),true);
		modeleMatiere = EtatIhm.getInstance().getMatiere();
		modeleMatiere.addPropertyChangeListener(Matiere.TITRE,
				pce -> setTitle(modeleMatiere.getTitre()));
		modeleMatiere.addPropertyChangeListener(Matiere.LISTE_THEMES,
				pce -> majListeThemes());
		init();
		pack();
		setVisible(true);
	}
	
	class ValideurFermeture extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent we) {
			if (!nomMatiere.getText().equals("")) {
				dispose();
			}
			else {
				nomMatiere.setBorder(BorderFactory.createLineBorder(Color.RED));
			}
		}
	}
	
	private void init() {
		nomMatiere = new JTextArea(3, 50);
		nomMatiere.setText(modeleMatiere.getTitre());
		nomMatiere.addCaretListener(ce -> modeleMatiere.setTitre(nomMatiere.getText()));
		add(nomMatiere, BorderLayout.NORTH);
		listeTheme = new JPanel();
		listeTheme.setLayout(new BoxLayout(listeTheme,BoxLayout.Y_AXIS));
		majListeThemes();
		add(new JScrollPane(listeTheme, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		ajouter = new JButton("Ajouter Theme");
		ajouter.addActionListener(ae -> new DialogueEditionTheme(this, modeleMatiere));
		JPanel boutons = new JPanel();
		boutons.add(ajouter);
		add(boutons,BorderLayout.SOUTH);
		addWindowListener(new ValideurFermeture());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	private void majListeThemes() {
		listeTheme.removeAll();
		for (Theme theme : modeleMatiere) {
			listeTheme.add(new PanelResumeTheme(this, theme, modeleMatiere));
		}
		listeTheme.updateUI();
	}
}
