package org.mermet.editeurQcm.interro.ihm.parametres;

import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.generateur.GenerationSite;
import org.mermet.editeurQcm.interro.generateur.GenerationXML;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class DialogueParametresSite extends DialogueParametres {

    private String password;

    public DialogueParametresSite(StructureQcm maStructure) {
        super(maStructure);
    }

    @Override
    protected void creationComposant() {

    }

    @Override
    protected void dessinDesPanneauxParametres() {

    }

    @Override
    protected void dessinDesPanneauxControle() {

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
        valider.addActionListener(e -> {
            File dossierXML = new File("../siteQCM/ressources/data");
            GenerationXML generateurXml = new GenerationXML(dossierXML, structureQcm, (Integer) saisieNombre.getValue());
            generateurXml.generer();
            GenerationSite generateur = new GenerationSite(fichierChoisi, password);
            generateur.generer();
            dispose();
        });
    }
}
