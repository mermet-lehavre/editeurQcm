package org.mermet.editeurQcm.interro.ihm.parametres;

import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.generateur.GenerationPdf;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class DialogueParametresCopie extends DialogueParametres {

	public DialogueParametresCopie(StructureQcm maStructure) {
        super(maStructure);
        fichierChoisi = new File("qcm.pdf");
        init();
        pack();
        setVisible(true);
	}

    @Override
    protected void creationComposant() {
        labelNombre = new JLabel("Nombre de copies à générer : ");
        SpinnerNumberModel modeleNombre = new SpinnerNumberModel(1, 1, 500, 1);
        saisieNombre = new JSpinner(modeleNombre);
        labelFichier = new JLabel("Fichier cible : ");
        nomFichier = new JTextField(20);
        nomFichier.setEditable(false);
        nomFichier.setText(fichierChoisi.getName());
        naviguer = new JButton("Naviguer");
        valider = new JButton("Valider");
        annuler = new JButton("Annuler");
    }

    @Override
    protected void dessinDesPanneauxParametres() {
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
    }

    @Override
    protected void ajoutBoutons() {
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
