package org.mermet.editeurQcm.interro.generateur;

import org.zeroturnaround.zip.ZipUtil;

import java.io.File;

public class GenerationSite {
    private File dossierChoisi;
    private String password;

    public GenerationSite(File dossierChoisi, String password) {
        this.dossierChoisi = dossierChoisi;
        this.password = password;
    }

    public void generer() {

        // TODO Sélection du dossier où l'on importer le zip
        // TODO Sélection du password pour la partie admin a changer dans le fichier admin.xml

        // Création du dossier compressé
        ZipUtil.pack(new File("siteQCM"), new File(dossierChoisi.getAbsolutePath() + "/siteQCM.zip"));
    }
}
