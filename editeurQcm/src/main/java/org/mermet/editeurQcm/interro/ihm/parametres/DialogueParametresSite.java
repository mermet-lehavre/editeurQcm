package org.mermet.editeurQcm.interro.ihm.parametres;

import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.generateur.GenerationPdf;
import org.mermet.editeurQcm.interro.generateur.GenerationSite;
import org.mermet.editeurQcm.interro.generateur.GenerationXML;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DialogueParametresSite extends DialogueParametres {

    private String password;
    private JLabel labelPwdAdmin;
    private JPasswordField pwdAdmin;

    public DialogueParametresSite(StructureQcm maStructure) {
        super(maStructure);
    }

    @Override
    protected void creationComposant() {
        labelPwdAdmin = new JLabel("Mot de passe Administrateur : ");
        pwdAdmin = new JPasswordField(20);
        labelNombre = new JLabel("Nombre de copies : ");
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
        JPanel panelAdmin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAdmin.add(labelPwdAdmin);
        panelAdmin.add(pwdAdmin);
        panelInfos.add(panelAdmin);

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
            password = String.valueOf(pwdAdmin.getPassword());

            File dossierXML = new File("../siteQCM/ressources/data");
            GenerationXML generateurXml = new GenerationXML(dossierXML, structureQcm, (Integer) saisieNombre.getValue());
            generateurXml.generer();
            GenerationSite generateur = new GenerationSite(fichierChoisi, password);
            generateur.generer();
            GenerationPdf generateurPdf = new GenerationPdf(fichierChoisi);
            generateurPdf.genererCode(generateurXml.getCodes());
            dispose();
        });
    }


}
