package org.mermet.editeurQcm.interro.generateur;

import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;

public class GenerationSite {
    private GenerationXML generationXML;

    public GenerationSite(File dossierChoisi, StructureQcm structureQcm, Integer nbCopies) {
        generationXML = new GenerationXML(dossierChoisi, structureQcm, nbCopies);
    }

    public void generer() {
        generationXML.generer();

        /* TODO
            Sélection du dossier où l'on importer le zip
            Sélection du password pour la partie admin a changer dans le fichier admin.xml
         */

        // Création du dossier compressé
        ZipUtil.pack(new File("../siteQCM"), new File("siteQCM.zip"));
    }
}
