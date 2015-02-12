/**
 * @author : Groupe Sires
 */

package org.mermet.editeurQcm.interro.ihm;

import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.generateur.GenerationXML;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DialogueDestinationXml extends JDialog {
    private JSpinner saisieNombre;
    private JTextField nomFichier;
    private JButton naviguer;
    private JButton valider;
    private JButton annuler;
    private File dossierChoisi;
    private StructureQcm structureQcm;

    public DialogueDestinationXml(StructureQcm maStructure) {
        super((Frame) null, "Paramètres", true);
        structureQcm = maStructure;
        init();
        pack();
        setVisible(true);
    }

    private void init() {
        /* Création des composants */
        JLabel labelNombre = new JLabel("Nombre des étudiants : ");
        SpinnerNumberModel modeleNombre = new SpinnerNumberModel(1, 1, 500, 1);
        saisieNombre = new JSpinner(modeleNombre);
        JLabel labelFichier = new JLabel("Fichier XML cible : ");
        nomFichier = new JTextField(20);
        nomFichier.setEditable(false);
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
                    selecteur.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    selecteur.setSelectedFile(new File("qcm.xml"));
                    int selection = selecteur.showSaveDialog(this);
                    if (selection == JFileChooser.APPROVE_OPTION) {
                        dossierChoisi = selecteur.getSelectedFile();
                        nomFichier.setText(dossierChoisi.toString());
                    }
                }
        );
        annuler.addActionListener(ae -> dispose());

        valider.addActionListener(e -> {
            GenerationXML generateur = new GenerationXML(dossierChoisi,
                    structureQcm,
                    (Integer) saisieNombre.getValue());
            generateur.generer();
            dispose();
        });
    }
}
