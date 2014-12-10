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

import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Theme;

public class PanelResumeQuestion extends JPanel {
	private Question modeleQuestion;
	private JLabel enonce;
	private JButton supprimer;
	private JButton editer;
	private Theme theme;
	private JDialog fenetre;
	
	public PanelResumeQuestion(JDialog maFenetre, Question question, Theme monTheme) {
			fenetre = maFenetre;
			modeleQuestion = question;
			modeleQuestion.addPropertyChangeListener(Question.ENONCE, 
					mq -> {setEnonce();});
			theme = monTheme;
			init();
	}
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		enonce = new JLabel("                                        ");
		setEnonce();
		add(enonce);
		add(Box.createHorizontalGlue());
		JPanel panelBouton = new JPanel();
		panelBouton.setLayout(new BoxLayout(panelBouton, BoxLayout.Y_AXIS));
		supprimer = new JButton("Supprimer");
		supprimer.addActionListener(ae -> {theme.removeQuestion(modeleQuestion);});
		panelBouton.add(supprimer);
		editer = new JButton("Editer");
		editer.addActionListener(ae -> new DialogueQuestion(fenetre, modeleQuestion));
		panelBouton.add(editer);
		add(panelBouton);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));		
	}
	
	private void setEnonce() {
		String titre = modeleQuestion.getEnonce();
		if (titre.length() > 40) {
				titre = titre.substring(0, 40) + "...";
		}
		enonce.setText(titre);
	}
}
