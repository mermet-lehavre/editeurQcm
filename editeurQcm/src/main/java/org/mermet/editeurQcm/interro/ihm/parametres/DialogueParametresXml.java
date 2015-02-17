/**
 * @author : Groupe Sires
 */

package org.mermet.editeurQcm.interro.ihm.parametres;

import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.generateur.GenerationPdf;
import org.mermet.editeurQcm.interro.generateur.GenerationXML;

import javax.swing.*;
import java.awt.*;

public class DialogueParametresXml extends DialogueParametres {

    public DialogueParametresXml(StructureQcm maStructure) {
        super(maStructure);
        init();
        pack();
        setVisible(true);
    }

    @Override
    protected void creationComposant() {
        labelNombre = new JLabel("Nombre des étudiants : ");
        SpinnerNumberModel modeleNombre = new SpinnerNumberModel(1, 1, 500, 1);
        saisieNombre = new JSpinner(modeleNombre);
        labelFichier = new JLabel("Dossier cible : ");
        nomFichier = new JTextField(20);
        nomFichier.setEditable(false);
        naviguer = new JButton("Naviguer");
        valider = new JButton("Valider");
        valider.setEnabled(false);
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
                    selecteur.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int selection = selecteur.showSaveDialog(this);
                    if (selection == JFileChooser.APPROVE_OPTION) {
                        fichierChoisi = selecteur.getSelectedFile();
                        nomFichier.setText(fichierChoisi.toString());
                        valider.setEnabled(true);
                    }
                }
        );
        annuler.addActionListener(ae -> dispose());

        valider.addActionListener(e -> {
            GenerationXML generateur = new GenerationXML(fichierChoisi,
                    structureQcm,
                    (Integer) saisieNombre.getValue());
            generateur.generer();
            
            GenerationPdf generateurPdf = new GenerationPdf(fichierChoisi);
            generateurPdf.genererCode(generateur.getCodes());
            dispose();
        });
    }
}
