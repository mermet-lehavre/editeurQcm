package org.mermet.editeurQcm.ihm.composants;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Theme;

public class DialogueQuestion extends JDialog {
	private PanelEditionQuestion edition;
	private Question question;
	private Theme theme;
	private JPanel panneauBoutons;
	
	public DialogueQuestion(JDialog fenetrePrincipale, Question maQuestion) {
		super(fenetrePrincipale, maQuestion.getEnonce(), true);
		question = maQuestion;
		initEdition(question);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public DialogueQuestion(JDialog fenetrePrincipale, Theme monTheme) {
		super(fenetrePrincipale, "", true);
		theme = monTheme;
		question = new Question();
		initAjout(question);
		pack();
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void init(Question question) {
		panneauBoutons = new JPanel();
		add(panneauBoutons, BorderLayout.SOUTH);
		edition = new PanelEditionQuestion(question, this);
		add(edition,BorderLayout.CENTER);		
	}
	private void initEdition(Question question) {
		init(question);
		JButton fermer = new JButton("Fermer");
		fermer.addActionListener(ae -> dispose());
		panneauBoutons.add(fermer);
	}

	private void initAjout(Question question) {
		init(question);
		JButton ajouter = new JButton("Ajouter");
		ajouter.addActionListener(ae -> {theme.addQuestion(question); dispose();});
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(ae -> {dispose();});
		panneauBoutons.add(ajouter);
		panneauBoutons.add(annuler);
	}
}
