package org.mermet.editeurQcm;

import java.io.File;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Reponse;
import org.mermet.editeurQcm.donnees.Theme;
import org.xml.sax.InputSource;
/**
 *
 * @author brunomermet
 */
public class LecteurMatiere {
    private Document document;

    public LecteurMatiere(final File fichier) {
        final SAXBuilder sxb = new SAXBuilder();
        try {
           document = sxb.build(fichier);
        }
        catch (Exception e) {
            System.err.println("Probleme jdom : " + e.getMessage());
            System.exit(0);
        }
    }

    public LecteurMatiere(final String chaine) {
        final SAXBuilder sxb = new SAXBuilder();
        StringReader lecteur = new StringReader(chaine);
        InputSource source = new InputSource(lecteur);
        try {
           document = sxb.build(source);
        }
        catch (Exception e) {
            System.err.println("Probleme jdom : " + e.getMessage());
            System.exit(0);
        }
    }

    public Matiere getExamen() {
    	final Element racine = document.getRootElement();
    	final Matiere matiere = new Matiere(racine);
    	return matiere;
    }
    
}
