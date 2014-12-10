package org.mermet.editeurQcm.ihm.composants;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Theme;

public class PanelResumeTheme extends JPanel {
	private Theme modeleTheme;
	private JLabel intitule;
	private JButton supprimer;
	private JButton editer;
	private Matiere matiere;
	private JDialog fenetre;
	
	public PanelResumeTheme(JDialog maFenetre, Theme monTheme, Matiere maMatiere) {
			fenetre = maFenetre;
			modeleTheme = monTheme;
			modeleTheme.addPropertyChangeListener(Theme.INTITULE, 
					mq -> {setIntitule();});
			matiere = maMatiere;
			init();
	}
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		intitule = new JLabel("                                        ");
		setIntitule();
		add(intitule);
		add(Box.createHorizontalGlue());
		JPanel panelBouton = new JPanel();
		panelBouton.setLayout(new BoxLayout(panelBouton, BoxLayout.Y_AXIS));
		supprimer = new JButton("Supprimer");
		supprimer.addActionListener(ae -> {matiere.remove(modeleTheme);});
		panelBouton.add(supprimer);
		editer = new JButton("Editer");
		editer.addActionListener(ae -> new DialogueEditionTheme(fenetre, modeleTheme));
		panelBouton.add(editer);
		add(panelBouton);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));		
	}
	
	private void setIntitule() {
		String titre = modeleTheme.getIntitule();
		if (titre.length() > 40) {
				titre = titre.substring(0, 40) + "...";
		}
		intitule.setText(titre);
	}
}
