package org.mermet.editeurQcm.ihm.composants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JFrame;

import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Reponse;

public class PanelEditionQuestion extends JPanel {
	private JTextArea texteQuestion;
	private Question modeleQuestion;
	private JPanel listeReponses;
	private PanelAjoutReponse panelAjoutReponse;
	private JDialog fenetre;
	
	
	public PanelEditionQuestion(Question question, JDialog maFenetre) {
		fenetre = maFenetre;
		init();
		modeleQuestion = question;
		texteQuestion.setText(modeleQuestion.getEnonce());
		modeleQuestion
			.getPropositions()
			.stream()
			.forEach(x-> {
				JPanel panneauEdit = new PanelEditionReponse(x, this);
				listeReponses.add(panneauEdit);});
		listeReponses.add(panelAjoutReponse);
		modeleQuestion.addPropertyChangeListener(Question.ENONCE,
				pce -> maFenetre.setTitle(modeleQuestion.getEnonce()));
		}
	
	public void init() {
		setLayout(new BorderLayout());
		texteQuestion = new JTextArea(3,80);
		add(texteQuestion, BorderLayout.NORTH);
		
		listeReponses = new JPanel();
		listeReponses.setLayout(new BoxLayout(listeReponses, BoxLayout.Y_AXIS));
		add(new JScrollPane(listeReponses,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
				),
			BorderLayout.CENTER
		);
		panelAjoutReponse = new PanelAjoutReponse(this);
		texteQuestion.addCaretListener(
				ce -> modeleQuestion.setEnonce(texteQuestion.getText()));
	}

	public void supprimerReponse(PanelEditionReponse panneauReponse, Reponse reponse) {
		modeleQuestion.removeReponse(reponse);
		listeReponses.remove(panneauReponse);
		listeReponses.updateUI();
	}

	public void ajouterReponse(String text, boolean selected) {
		Reponse nouvelleReponse = new Reponse(text, selected);
		modeleQuestion.addReponse(nouvelleReponse);
		JPanel nouveauPanneau = new PanelEditionReponse(nouvelleReponse,this);
		listeReponses.remove(panelAjoutReponse);
		listeReponses.add(nouveauPanneau);
		panelAjoutReponse.nettoyer();
		listeReponses.add(panelAjoutReponse);
		listeReponses.updateUI();
	}
}
