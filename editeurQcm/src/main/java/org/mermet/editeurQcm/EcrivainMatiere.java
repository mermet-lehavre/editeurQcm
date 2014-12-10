package org.mermet.editeurQcm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Optional;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.mermet.editeurQcm.donnees.Matiere;

public class EcrivainMatiere {
	Optional<Writer> sortieFichier;
	Writer sortieString;
	
	public EcrivainMatiere(File fichier) {
		sortieString = new StringWriter();
		try {
			sortieFichier = Optional.of(new FileWriter(fichier));
		}
		catch(IOException e) {
			System.err.println("pb fichier sortie : " + e.getMessage());			
		}
	}
	
	public EcrivainMatiere() {
		sortieString = new StringWriter();
		sortieFichier = Optional.empty();
	}
	
	public String ecrire(Matiere matiere) {
		Element racine = matiere.toXML();
		Document document = new Document(racine);
		XMLOutputter ecrivain = new XMLOutputter(Format.getPrettyFormat());
		try {
			sortieFichier.ifPresent(
					x -> { try {
								ecrivain.output(document, x);
							}
							catch(IOException ioe){}
					});
			ecrivain.output(document, sortieString);
		}
		catch (IOException ioe) {
			System.err.println("pb ecriture xml : " + ioe.getMessage());
		}
		return sortieString.toString();
	}
}
