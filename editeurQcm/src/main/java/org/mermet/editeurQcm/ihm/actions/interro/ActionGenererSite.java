package org.mermet.editeurQcm.ihm.actions.interro;

import org.mermet.editeurQcm.ihm.EtatIhm;
import org.mermet.editeurQcm.ihm.actions.ActionPerso;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.ihm.DialogueParametresSite;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionGenererSite extends ActionPerso {
    private static ActionGenererSite instance = null;
    private ActionGenererSite() {
        super("Générer le site Web");
        setEnabled(false);
    }
    public static ActionGenererSite getInstance() {
        if (instance == null) {
            instance = new ActionGenererSite();
        }
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StructureQcm structure = EtatIhm.getInstance().getStructureQcm();
        JDialog parametres = new DialogueParametresSite(structure);
    }
}
