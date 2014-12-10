package org.mermet.editeurQcm.interro.ihm;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.mermet.editeurQcm.interro.donnees.PartieStructure;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;

public class PanelResumePartie extends JPanel {
	private PartieStructure partie;
	private StructureQcm qcm;
	private JLabel resume;
	private JButton supprimer;

	public PanelResumePartie(PartieStructure maPartie, StructureQcm monQcm) {
		partie = maPartie;
		qcm = monQcm;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		resume = new JLabel();
		resume.setText(partie.getResume());
		add(resume);
		add(Box.createHorizontalGlue());
		supprimer = new JButton("Supprimer");
		supprimer.addActionListener(ae -> monQcm.removePartie(partie));
		add(supprimer);
	}
}
