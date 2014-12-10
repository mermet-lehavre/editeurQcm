package org.mermet.editeurQcm.interro.ihm;

import java.awt.Color;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


import org.mermet.editeurQcm.donnees.Matiere;import org.mermet.editeurQcm.donnees.Question;


public class PanelQuestion extends JPanel {
	private JComboBox choixTheme;
	private JComboBox<Question> choixQuestion;
	private JButton supprimer;
	private Matiere matiere;
	
	public PanelQuestion(Matiere maMatiere) {
		matiere = maMatiere;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		choixTheme = new JComboBox(matiere.toArray());
		choixTheme.addActionListener(
				ae -> {
					int indice = choixTheme.getSelectedIndex();
					choixQuestion.setModel(
						new DefaultComboBoxModel<Question>(
								matiere
								.getTheme(indice)
								.getQuestions()
								.toArray(new Question[1])
						)
					);
				});
		choixQuestion = new JComboBox(matiere.getTheme(0).getQuestions().toArray());
		supprimer = new JButton("Supprimer");
		add(choixTheme);
		add(Box.createHorizontalGlue());
		add(choixQuestion);
		add(Box.createHorizontalGlue());
		add(supprimer);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public Question getQuestion() {
		Question question = (Question) choixQuestion.getSelectedItem();
		return question;
	}
}
