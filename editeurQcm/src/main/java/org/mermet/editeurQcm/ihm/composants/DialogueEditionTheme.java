package org.mermet.editeurQcm.ihm.composants;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Theme;

public class DialogueEditionTheme extends JDialog {
	private Theme modeleTheme;
	private JTextArea titreTheme;
	private JPanel listeQuestions;
	private JButton ajouter;
	private Matiere matiere;
	private JPanel boutons;
	
	/**
	 * Constructeur pour l'édition
	 * @param fenetre la fenêtre à laquelle est rattachée la boîte de dialogue
	 * @param le thème que l'on va éditer
	 */
	public DialogueEditionTheme(JDialog fenetre, Theme theme) {
		super(fenetre,theme.getIntitule(),true);
		modeleTheme = theme;
		modeleTheme.addPropertyChangeListener(Theme.INTITULE,
				pce -> setTitle(modeleTheme.getIntitule()));
		modeleTheme.addPropertyChangeListener(Theme.LISTE_QUESTIONS,
				pce -> majListeQuestions());
		
		initEdition();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public DialogueEditionTheme(JDialog fenetre, Matiere maMatiere) {
		super(fenetre,"",true);
		matiere = maMatiere;
		modeleTheme = new Theme("");
		modeleTheme.addPropertyChangeListener(Theme.INTITULE,
				pce -> setTitle(modeleTheme.getIntitule()));
		modeleTheme.addPropertyChangeListener(Theme.LISTE_QUESTIONS,
				pce -> majListeQuestions());
		
		initCreation();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void init() {
		titreTheme = new JTextArea(3, 50);
		titreTheme.setText(modeleTheme.getIntitule());
		titreTheme.addCaretListener(ce -> modeleTheme.setIntitule(titreTheme.getText()));
		add(titreTheme, BorderLayout.NORTH);
		listeQuestions = new JPanel();
		listeQuestions.setLayout(new BoxLayout(listeQuestions,BoxLayout.Y_AXIS));
		majListeQuestions();
		add(new JScrollPane(listeQuestions, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		ajouter = new JButton("Ajouter Question");
		ajouter.addActionListener(ae -> new DialogueQuestion(this, modeleTheme));
		boutons = new JPanel();
		boutons.setLayout(new BoxLayout(boutons, BoxLayout.X_AXIS));
		boutons.add(ajouter);
		boutons.add(Box.createHorizontalGlue());
		add(boutons,BorderLayout.SOUTH);
	}
	
	private void initEdition() {
		init();
		JButton fermer = new JButton("Fermer");
		fermer.addActionListener(ae -> dispose());
		boutons.add(fermer);
	}
	
	private void initCreation() {
		init();
		JButton valider = new JButton("Valider");
		valider.addActionListener(ae -> {matiere.add(modeleTheme);dispose();});
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(ae -> {dispose();});
		boutons.add(valider);
		boutons.add(annuler);
	}
	
	private void majListeQuestions() {
		listeQuestions.removeAll();
		/*for (Question question : modeleTheme.getQuestions()) {
			listeQuestions.add(new PanelResumeQuestion(this, question, modeleTheme));
		}*/
		modeleTheme.getQuestions().stream().forEach(question ->
			listeQuestions.add(new PanelResumeQuestion(this, question, modeleTheme)));
		listeQuestions.updateUI();
	}
}
