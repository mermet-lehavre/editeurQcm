package org.mermet.editeurQcm.interro.ihm;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.ihm.EtatIhm;
import org.mermet.editeurQcm.interro.donnees.PartieStructure;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;

public class DialogueInterro extends JDialog {
	private StructureQcm modeleQcm;
	private Matiere matiere;
	private JTextField titre;
	private JTextField date;
	private JTextField duree;
	private JTextArea avantPropos;
	private JPanel resumes;
	
	public DialogueInterro(Matiere maMatiere) {
		super((Frame) null, "", true);
		//super(FenetrePrincipale.getInstance(), "", true);
		matiere = maMatiere;
		modeleQcm = new StructureQcm("", "", "", "");
		init();
		modeleQcm.addPropertyChangeListener(StructureQcm.LISTE_PARTIES,
				pce -> majResumes());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void majResumes() {
		resumes.removeAll();
		for (PartieStructure partie : modeleQcm.getParties()) {
			resumes.add(new PanelResumePartie(partie, modeleQcm));
		}
		resumes.updateUI();
	}

	private JPanel creerEnTete() {
		/* Création de l'en-tête */
		JPanel enTete = new JPanel();
		enTete.setLayout(new BoxLayout(enTete, BoxLayout.Y_AXIS));

		titre = new JTextField(50);
		JLabel labelTitre = new JLabel("Titre :");
		labelTitre.setLabelFor(titre);
		titre.setFont(titre.getFont().deriveFont(24.0f));
		JPanel panneauTitre = new JPanel();
		panneauTitre.add(labelTitre);
		panneauTitre.add(titre);
		enTete.add(panneauTitre);
		
		date = new JTextField(15);
		JLabel labelDate = new JLabel("Date :");
		labelDate.setLabelFor(date);
		duree = new JTextField(20);
		JLabel labelDuree = new JLabel("Durée :");
		labelDuree.setLabelFor(duree);
		JPanel panneauPrecisions = new JPanel();
		panneauPrecisions.setLayout(new BoxLayout(panneauPrecisions, BoxLayout.X_AXIS));
		panneauPrecisions.add(labelDate);
		panneauPrecisions.add(date);
		panneauPrecisions.add(Box.createHorizontalGlue());
		panneauPrecisions.add(labelDuree);
		panneauPrecisions.add(duree);
		enTete.add(panneauPrecisions);
		
		avantPropos = new JTextArea(4,60);
		JPanel panneauAvantPropos = new JPanel();
		panneauAvantPropos.add(avantPropos);
		panneauAvantPropos.setBorder(
				BorderFactory.createTitledBorder(
						BorderFactory.createLineBorder(Color.BLACK), "Avant-Propos"));
		enTete.add(panneauAvantPropos);
		return enTete;
	}
	private JScrollPane creerResumesParties() {
		/* Création de la liste des résumés des parties */
		resumes = new JPanel();
		resumes.setLayout(new BoxLayout(resumes, BoxLayout.Y_AXIS));
		JScrollPane defilementResumes = new JScrollPane(resumes,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		defilementResumes.setBorder(
				BorderFactory.createTitledBorder(
						BorderFactory.createLineBorder(Color.BLACK),
						"Parties"));
		defilementResumes.setPreferredSize(new Dimension(300,100));
		return defilementResumes;
	}
	
	private JPanel creerPanneauCommandes() {
		/* Création du panneau de commandes */
		JPanel boutons = new JPanel();
		boutons.setLayout(new BoxLayout(boutons, BoxLayout.X_AXIS));
		JButton ajouterPartie = new JButton("Ajouter partie");
		Object[] options = {"thème", "liste de questions"};
		ajouterPartie.addActionListener(
				ae -> {
					DialogueCreationPartie dialogueCreationPartie;
					int choix = JOptionPane.showOptionDialog(DialogueInterro.this,
							"Choix de la source",
							"Type de partie",
							JOptionPane.OK_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null, 
							options,
							"thème");
					switch(choix) {
					case 0:
						dialogueCreationPartie = new DialogueCreationPartieTheme(DialogueInterro.this, matiere, modeleQcm);
						break;
					default:
						dialogueCreationPartie = new DialogueCreationPartieListeQuestions(DialogueInterro.this, matiere, modeleQcm);
					}
				});
		boutons.add(ajouterPartie);
		boutons.add(Box.createHorizontalGlue());
		JButton fermer = new JButton("Fermer");
		fermer.addActionListener(ae -> {
			modeleQcm.setTitre(titre.getText());
			modeleQcm.setDate(date.getText());
			modeleQcm.setDuree(duree.getText());
			modeleQcm.setAvantPropos(avantPropos.getText());
			EtatIhm.getInstance().setStructureQcm(modeleQcm);
			dispose();
		});
		boutons.add(fermer);
		return boutons;
	}
	private void init() {
		JPanel enTete = creerEnTete();
		add(enTete, BorderLayout.NORTH);
		
		JScrollPane defilementResumes = creerResumesParties();
		add(defilementResumes, BorderLayout.CENTER);
		
		JPanel boutons = creerPanneauCommandes();
		add(boutons, BorderLayout.SOUTH);
	}
}
