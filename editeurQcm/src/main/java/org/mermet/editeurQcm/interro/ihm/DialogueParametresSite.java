package org.mermet.editeurQcm.interro.ihm;

import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.generateur.GenerationSite;
import org.mermet.editeurQcm.interro.generateur.GenerationXML;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DialogueParametresSite extends JDialog {

    private StructureQcm structureQcm;
    private JButton valider;
    private JSpinner saisieNombre;
    private File dossierChoisi;

    public DialogueParametresSite(StructureQcm maStructure) {
        super((Frame) null, "Paramètres", true);
        structureQcm = maStructure;
        init();
        pack();
        setVisible(true);
    }

    private void init() {
        // TODO



        valider.addActionListener(e -> {
            GenerationSite generateur = new GenerationSite(dossierChoisi,
                    structureQcm,
                    (Integer) saisieNombre.getValue());
            generateur.generer();
            dispose();
        });
    }
}
