package org.mermet.editeurQcm.interro.ihm;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.interro.donnees.PartieStructure;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;

public abstract class DialogueCreationPartie extends JDialog {
	private JTextField titrePartie;
	protected JTextField saisieNbQuestions;
	protected PartieStructure partie;
	protected Matiere matiere;
	private JButton valider;
	private JButton annuler;
	private int nbQuestions;
	private StructureQcm qcm;

	public DialogueCreationPartie(JDialog parent, Matiere maMatiere, StructureQcm monQcm) {
		super(parent, "Création partie", true);
		matiere = maMatiere;
		qcm = monQcm;
		JPanel enTete = new JPanel();
		enTete.setLayout(new BoxLayout(enTete, BoxLayout.Y_AXIS));
		partie = new PartieStructure("", 0);
		titrePartie = new JTextField(40);
		saisieNbQuestions = new JTextField(4);
		enTete.add(titrePartie);
		enTete.add(saisieNbQuestions);
		add(enTete, BorderLayout.NORTH);
		init();
		JButton valider = new JButton("Valider");
		JButton annuler = new JButton("Annuler");
		JPanel panneauBoutons = new JPanel();
		panneauBoutons.add(valider);
		panneauBoutons.add(annuler);
		valider.addActionListener(ae -> {if (valider()) {dispose();majPartie();qcm.addPartie(partie);}});
		annuler.addActionListener(ae -> {dispose(); partie.setSource(null);});
		add(panneauBoutons, BorderLayout.SOUTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public abstract void init();
	
	public void majPartie() {
		partie.setTitre(titrePartie.getText());
		partie.setNbQuestions(nbQuestions);
		majSource();
	}
	public abstract void majSource();

	public abstract boolean validerSpecifique();
	
	public boolean valider() {
		return validerNB() && validerTitrePartie() && validerSpecifique();
	}

	public PartieStructure getPartie() {
		return partie;
	}
	
	public int getNbQuestions() {
		return nbQuestions;
	}

	public boolean validerNB() {
		try {
			nbQuestions = Integer.parseInt(saisieNbQuestions.getText());
			return true;
		}
		catch(NumberFormatException nfe) {
			return false;
		}
	}
	
	public boolean validerTitrePartie() {
		return titrePartie.getText().length() > 0;
	}
}
