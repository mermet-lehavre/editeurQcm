package org.mermet.editeurQcm.interro.generateur;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerationSite {
    private File dossierChoisi;
    private String password;

    public GenerationSite(File dossierChoisi, String password) {
        this.dossierChoisi = dossierChoisi;
        this.password = password;
    }

    public void generer() {

        try {
            majPwd(password);
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

        // Création du dossier compressé
        ZipUtil.pack(new File("../siteQCM"), new File(dossierChoisi.getAbsolutePath() + "/siteQCM.zip"));
    }

    public void majPwd(String newPwd) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        String xmlPath = "../siteQCM/ressources/data/admin.xml";
        File xmlFile = new File(xmlPath);
        Document doc = builder.build(xmlFile);
        Element rootNode = doc.getRootElement();

        Element staff = rootNode.getChild("password");
        staff.setText(newPwd);
        XMLOutputter xmlOutput = new XMLOutputter();

        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(doc, new FileWriter(xmlPath));

        System.out.println("File updated!");
    }
}
