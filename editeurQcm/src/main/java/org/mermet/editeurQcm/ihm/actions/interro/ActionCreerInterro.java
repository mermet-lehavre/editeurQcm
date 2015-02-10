package org.mermet.editeurQcm.ihm.actions.interro;

import java.awt.event.ActionEvent;

import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.ihm.EtatIhm;
import org.mermet.editeurQcm.ihm.actions.ActionPerso;
import org.mermet.editeurQcm.interro.ihm.DialogueInterro;

public class ActionCreerInterro extends ActionPerso {
    private static ActionCreerInterro instance = null;

    private ActionCreerInterro() {
        super("Créer");
        setEnabled(false);
    }

    public static ActionCreerInterro getInstance() {
        if (instance == null) {
            instance = new ActionCreerInterro();
        }
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Matiere matiere = EtatIhm.getInstance().getMatiere();
        DialogueInterro dialogue = new DialogueInterro(matiere);
        ActionGenererDevoir.getInstance().setEnabled(true);
        ActionGenererXml.getInstance().setEnabled(true);
    }

}
