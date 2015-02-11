/**
 * @author : Groupe Sires
 */
package org.mermet.editeurQcm.interro.generateur;

import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Reponse;
import org.mermet.editeurQcm.interro.donnees.IQcm;
import org.mermet.editeurQcm.interro.donnees.PartieQcm;
import org.mermet.editeurQcm.interro.donnees.Qcm;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;


public class GenerateXML {

    private StructureQcm structure;
    private File fichierSortie;
    private int nbCopies;

    // Def Element
    private Document doc;

    public GenerateXML(File monFichierSortie, StructureQcm maStructure, Integer monNbCopies) {
        structure = maStructure;
        fichierSortie = monFichierSortie;
        nbCopies = monNbCopies;

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void simpleElement(Document doc, Element parent, Element fils, String content) {
        fils.appendChild(doc.createTextNode(content));
        parent.appendChild(fils);
    }

    public void generer() {
        Element racine = preambuleInterro();

        for (int i = 1; i <= nbCopies; i++) {
            IQcm qcm = new Qcm(structure);

            Element eQcm = preambuleQCM(racine);
            List<PartieQcm> liste = qcm.getListeParties();
            for (PartieQcm partie : liste) {
                Element ePartie = afficherPartie(eQcm, partie.getTitre());

                for (Question question : partie.getQuestions()) {
                    Element eQuestion = afficherQuestion(ePartie, question.getEnonce());
                    question.getPropositionsAleatoires().stream().forEach(reponse -> afficherReponse(eQuestion, reponse));
                }
            }
        }

        sauvegarde();
    }

    private void sauvegarde() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fichierSortie);

            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element preambuleInterro() {
        Element racine = doc.createElement("interro");
        doc.appendChild(racine);

        Element titreInterro = doc.createElement("titre-interro");
        Element date = doc.createElement("date");
        Element duree = doc.createElement("duree");
        Element avantPropos = doc.createElement("avant-propos");

        // Def arbre
        simpleElement(doc, racine, titreInterro, structure.getTitre());
        simpleElement(doc, racine, date, structure.getDate());
        simpleElement(doc, racine, duree, structure.getDuree());
        simpleElement(doc, racine, avantPropos, structure.getAvantPropos());

        return racine;
    }

    private Element preambuleQCM(Element racine) {
        Element qcm = doc.createElement("qcm");
        racine.appendChild(qcm);

        Element etudiant = doc.createElement("etudiant");
        qcm.appendChild(etudiant);

        int cle = (int) (Math.random() * 90000) + 10000;
        Element code = doc.createElement("code");
        code.appendChild(doc.createTextNode(String.valueOf(cle)));
        etudiant.appendChild(code);

        Element nom = doc.createElement("nom");
        etudiant.appendChild(nom);

        Element prenom = doc.createElement("prenom");
        etudiant.appendChild(prenom);

        Element note = doc.createElement("note");
        etudiant.appendChild(note);

        Element termine = doc.createElement("termine");
        qcm.appendChild(termine);

        return qcm;
    }

    private Element afficherPartie(Element qcm, String titre) {
        Element partie = doc.createElement("partie");
        qcm.appendChild(partie);

        Element eTitre = doc.createElement("titre");
        eTitre.appendChild(doc.createTextNode(titre));
        partie.appendChild(eTitre);

        return partie;
    }

    private Element afficherQuestion(Element ePartie, String enonce) {
        Element question = doc.createElement("question");
        ePartie.appendChild(question);

        Element eEnonce = doc.createElement("enonce");
        eEnonce.appendChild(doc.createTextNode(enonce));
        question.appendChild(eEnonce);

        return question;
    }

    private void afficherReponse(Element eQuestion, Reponse reponse) {
        Element eReponse = doc.createElement("reponse");
        eQuestion.appendChild(eReponse);

        Element eProposition = doc.createElement("proposition");
        eProposition.appendChild(doc.createTextNode(reponse.getProposition()));
        eReponse.appendChild(eProposition);

        Element eCorrect = doc.createElement("correct");
        eCorrect.appendChild(doc.createTextNode(String.valueOf(reponse.isCorrect())));
        eReponse.appendChild(eCorrect);

        Element eChoixEtudiant = doc.createElement("choix-etudiant");
        eReponse.appendChild(eChoixEtudiant);
    }
}