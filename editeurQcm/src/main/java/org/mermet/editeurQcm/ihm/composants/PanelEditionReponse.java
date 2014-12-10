package org.mermet.editeurQcm.ihm.composants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Reponse;

public class PanelEditionReponse  extends JPanel {
	private Reponse modeleReponse;
	private JLabel affichageNumero;
	private JButton supprimer;
	private JTextArea texteReponse;
	private JCheckBox caseCorrect;
	
	public PanelEditionReponse(Reponse reponse) {
		init();
		modeleReponse = reponse;
		affichageNumero.setText(""+reponse.getNumero());
		texteReponse.setText(reponse.getProposition());
		caseCorrect.setSelected(reponse.isCorrect());
		reponse.addPropertyChangeListener(Reponse.PROPOSITION,
				x-> texteReponse.setText(x.getNewValue().toString()));
		reponse.addPropertyChangeListener(Reponse.CORRECT,
				x-> caseCorrect.setSelected((Boolean) x.getNewValue()));
		reponse.addPropertyChangeListener(Reponse.NUMERO,
				x-> affichageNumero.setText(""+x.getNewValue()));
	}
	
	public PanelEditionReponse(Reponse reponse, PanelEditionQuestion panelQuestion) {
		this(reponse);
		supprimer.addActionListener(al -> panelQuestion.supprimerReponse(this,reponse));
	}
	
	private void init() {
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		affichageNumero = new JLabel();
		affichageNumero.setBackground(Color.YELLOW);
		affichageNumero.setForeground(Color.BLUE);
		affichageNumero.setOpaque(true);
		affichageNumero.setFont(affichageNumero.getFont().deriveFont(24.0f));
		add(affichageNumero);
		texteReponse = new JTextArea(3,50);
		caseCorrect = new JCheckBox("correct");
		add(texteReponse);
		add(Box.createHorizontalGlue());
		add(caseCorrect);
		supprimer = new JButton("Supprimer");
		add(supprimer);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
