package org.mermet.editeurQcm.interro.ihm;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.mermet.editeurQcm.ihm.fenetre.FenetrePrincipale;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.pdf.GenerationPdf;

public class DialogueParametresCopie extends JDialog {
	private JSpinner saisieNombre;
	private JTextField nomFichier;
	private JButton naviguer;
	private JButton valider;
	private JButton annuler;
	private File fichierChoisi;
	private StructureQcm structureQcm;
	
	public DialogueParametresCopie(StructureQcm maStructure) {
		super((Frame)null, "Paramètres", true);
		//super(FenetrePrincipale.getInstance(), "Paramètres", true);
		fichierChoisi = new File("qcm.pdf");
		structureQcm = maStructure;
		init();
		pack();
		setVisible(true);
	}
	private void init() {
		/* Création des composants */
		JLabel labelNombre = new JLabel("Nombre de copies à générer : ");
		SpinnerNumberModel modeleNombre = new SpinnerNumberModel(1, 1, 500, 1);
		saisieNombre = new JSpinner(modeleNombre);
		JLabel labelFichier = new JLabel("Fichier cible : ");
		nomFichier = new JTextField(20);
		nomFichier.setEditable(false);
		nomFichier.setText(fichierChoisi.getName());
		naviguer = new JButton("Naviguer");
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		
		/* Dessin du panneau des paramètres */
		JPanel panelInfos = new JPanel();
		panelInfos.setLayout(new BoxLayout(panelInfos, BoxLayout.Y_AXIS));
		JPanel panelNb = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelNb.add(labelNombre);
		panelNb.add(saisieNombre);
		panelInfos.add(panelNb);
		JPanel panelFichier = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelFichier.add(labelFichier);
		panelFichier.add(nomFichier);
		panelFichier.add(naviguer);
		panelInfos.add(panelFichier);
		add(panelInfos, BorderLayout.CENTER);
		
		/* Dessin du panneau de commandes */
		JPanel panneauCommandes = new JPanel();
		panneauCommandes.add(valider);
		panneauCommandes.add(annuler);
		this.getRootPane().setDefaultButton(valider);
		add(panneauCommandes, BorderLayout.SOUTH);
		
		/* Action des boutons */
		naviguer.addActionListener(
				ae -> {
					JFileChooser selecteur = new JFileChooser();
					selecteur.setFileFilter(new FileNameExtensionFilter("pdf", "pdf"));
					selecteur.setSelectedFile(new File("qcm.pdf"));
					int selection = selecteur.showSaveDialog(this);
					if (selection == JFileChooser.APPROVE_OPTION) {
						fichierChoisi = selecteur.getSelectedFile();
						nomFichier.setText(fichierChoisi.getName());
					}
				}
				);
		annuler.addActionListener(ae -> dispose());
		valider.addActionListener(
				ae -> {
					GenerationPdf generateur = new GenerationPdf(fichierChoisi,
							structureQcm,
							(Integer) saisieNombre.getValue());
					generateur.generer();
					dispose();
				});
	}
}
