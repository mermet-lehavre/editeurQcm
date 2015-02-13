package org.mermet.editeurQcm.interro.ihm.parametres;

import org.mermet.editeurQcm.interro.donnees.StructureQcm;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public abstract class DialogueParametres extends JDialog {

    protected JSpinner saisieNombre;
    protected JTextField nomFichier;
    protected JButton naviguer;
    protected JButton valider;
    protected JButton annuler;
    protected File fichierChoisi;
    protected StructureQcm structureQcm;
    protected JLabel labelNombre;
    protected JLabel labelFichier;

    public DialogueParametres(StructureQcm maStructure) {
        super((Frame) null, "Paramètres", true);
        structureQcm = maStructure;
        init();
        pack();
        setVisible(true);
    }

    protected void init() {
        creationComposant();
        dessinDesPanneauxParametres();
        dessinDesPanneauxControle();
        ajoutBoutons();
    }

    protected abstract void creationComposant();

    protected abstract void dessinDesPanneauxParametres();

    protected void dessinDesPanneauxControle() {
        JPanel panneauCommandes = new JPanel();
        panneauCommandes.add(valider);
        panneauCommandes.add(annuler);
        this.getRootPane().setDefaultButton(valider);
        add(panneauCommandes, BorderLayout.SOUTH);
    }

    protected abstract void ajoutBoutons();
}
