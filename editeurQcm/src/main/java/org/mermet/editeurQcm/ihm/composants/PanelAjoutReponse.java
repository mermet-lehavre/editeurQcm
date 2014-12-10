package org.mermet.editeurQcm.ihm.composants;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelAjoutReponse  extends JPanel {
	private JLabel affichageNumero;
	private JButton ajouter;
	private JTextArea texteReponse;
	private JCheckBox caseCorrect;
	
	public PanelAjoutReponse() {
		init();
		affichageNumero.setText("*");
	}
	
	public PanelAjoutReponse(PanelEditionQuestion panelQuestion) {
		this();
		ajouter.addActionListener(al -> panelQuestion.ajouterReponse(
				texteReponse.getText(),
				caseCorrect.isSelected()
				));
	}
	
	public void nettoyer() {
		texteReponse.setText("");
		caseCorrect.setSelected(false);
	}
	
	private final void init() {
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
		ajouter = new JButton("Ajouter");
		add(ajouter);
		setBorder(BorderFactory.createLineBorder(Color.RED));
	}
}
