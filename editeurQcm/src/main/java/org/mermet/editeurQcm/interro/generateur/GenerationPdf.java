package org.mermet.editeurQcm.interro.generateur;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Reponse;
import org.mermet.editeurQcm.interro.donnees.IQcm;
import org.mermet.editeurQcm.interro.donnees.PartieQcm;
import org.mermet.editeurQcm.interro.donnees.Qcm;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GenerationPdf {
	private StructureQcm structure;
	private File fichierSortie;
	private int nbCopies;
	private int largeur;
	private int hauteur;
	private int nbPages;
	private static final int MARGE = 20;
	private static final float INDENTATION_REPONSE = 30;
	private static final float ESPACE_AVANT_QUESTION = 10;
	private Document document;
	private PdfContentByte zone;
	private int numPageCourante;
	private int numQuestionCourante;

	
	public GenerationPdf(File monFichierSortie, StructureQcm maStructure, int monNbCopies) {
		structure = maStructure;
		fichierSortie = monFichierSortie;
		nbCopies = monNbCopies;
		document = new Document(PageSize.A4);
		largeur = (int) document.getPageSize().getWidth();
		hauteur = (int) document.getPageSize().getHeight();
	}

    public GenerationPdf(File fichierChoisi) {
        fichierSortie = fichierChoisi;
        document = new Document(PageSize.A4);
        largeur = (int) document.getPageSize().getWidth();
        hauteur = (int) document.getPageSize().getHeight();
    }


    public void generer() {
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(fichierSortie));
			document.open();
			zone = writer.getDirectContent();
			
			genererNcopies(nbCopies);
			
			} catch (DocumentException de) {
				de.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		document.close();
	}

	private void genererNcopies(int nb) {
		for (int i = 1 ; i <= nb; i++) {
			IQcm qcm = new Qcm(structure);
			generer1copie(qcm);
			if (i < nb) {
				document.newPage();
			}
		}
	}
	
	private void afficherTitrePartie(String titre) {
		Paragraph paraTitrePartie = new Paragraph(titre);
		paraTitrePartie.setSpacingBefore(20);
		Font policeTitre = paraTitrePartie.getFont();
		policeTitre.setSize(16f);
		paraTitrePartie.setFont(new Font(Font.FontFamily.HELVETICA, 16f));
		try {
			document.add(paraTitrePartie);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void generer1copie(IQcm qcm) {
		preambule();
		List<PartieQcm> liste = qcm.getListeParties();
		nbPages = liste
				.stream()
				.collect(Collectors.summingInt(PartieQcm::getNbQuestions))/4+1;
		numPageCourante = 1;
		numQuestionCourante = 1;
		for (PartieQcm partie : liste) {
			afficherTitrePartie(partie.getTitre());
			afficherQuestions(partie.getQuestions());
		}
	}

	private void preambule() {
		try {
			document.add(new Paragraph("\n\n"));
			
			Paragraph paraTitre = new Paragraph(structure.getTitre());
			Font policeTitre = paraTitre.getFont();
			policeTitre.setSize(24f);
			paraTitre.setFont(new Font(Font.FontFamily.HELVETICA, 24f));
			paraTitre.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paraTitre);

			document.add(new Paragraph("\n\n"));
			
			Paragraph paraPreambule = new Paragraph(structure.getAvantPropos());
			paraPreambule.setFont(new Font(Font.FontFamily.HELVETICA, 12f));
			paraPreambule.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			document.add(paraPreambule);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void afficherQuestions(List<Question> questions) {
		enTetePage();
		piedPage();
		/*for(int i = 0; i < questions.size(); i++) {
			afficherQuestion(questions.get(i), numQuestionCourante);
			numQuestionCourante++;
			if (numQuestionCourante % 4 == 0 && numPageCourante < nbPages) {
				numPageCourante++;
				changerPage();
			}
		}*/
		questions.stream().forEach(question ->
				{
					afficherQuestion(question, numQuestionCourante);
					numQuestionCourante++;
					if (numQuestionCourante %4 == 0 && numPageCourante < nbPages) {
						numPageCourante++;
						changerPage();
					}
				});
	}

	private void afficherQuestion(Question question, int num) {
		try {
			Paragraph paraQuestion = new Paragraph();
			paraQuestion.setSpacingBefore(ESPACE_AVANT_QUESTION);
			Phrase phraseNum = new Phrase("Question "+ num + ". ");
			Font policeNum = phraseNum.getFont();
			policeNum.setStyle(Font.BOLD);
			paraQuestion.add(phraseNum);
			Phrase phraseEnonce = new Phrase(question.getEnonce());
			paraQuestion.add(phraseEnonce);
			List<Reponse> reponses = question.getPropositionsAleatoires();
			for (Reponse reponse : reponses) {
				String caseACocher = "O ";
				System.out.println(caseACocher);
				Chunk coche = new Chunk(caseACocher);
				coche.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14f));
				Paragraph paraReponse = new Paragraph();
				paraReponse.add(coche);
				paraReponse.add(reponse.getProposition());
				paraReponse.setIndentationLeft(INDENTATION_REPONSE);
				paraReponse.setFirstLineIndent(-15);
				paraQuestion.add(paraReponse);
			}
			document.add(paraQuestion);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void changerPage() {
		document.newPage();
		enTetePage();
		piedPage();
		Paragraph saut = new Paragraph("\n\n\n\n");
		try {
			document.add(saut);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void enTetePage() {
		zone.beginText();
		//BaseFont policeDate = BaseFont.createFont(BaseFont.TIMES_ROMAN, "#simple", false);
		BaseFont policeDate = null;
		try {
			policeDate = BaseFont.createFont();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zone.setFontAndSize(policeDate, 10);
		zone.showTextAligned(Element.ALIGN_LEFT,
				structure.getDate(),
				MARGE,
				hauteur-MARGE-10,
				0);
		zone.showTextAligned(Element.ALIGN_RIGHT,
				structure.getDuree(),
				largeur-MARGE,
				hauteur-MARGE-10,
				0);
		zone.showTextAligned(Element.ALIGN_LEFT,
				"Nom :",
				MARGE,
				hauteur-MARGE-25,
				0);
		zone.showTextAligned(Element.ALIGN_LEFT,
				"Prénom :",
				MARGE,
				hauteur-MARGE-40,
				0);
		zone.endText();		
	}

	private void piedPage() {
		String numerotation = ""+numPageCourante+"/"+nbPages;
		zone.beginText();
		BaseFont policeDate = null;
		try {
			policeDate = BaseFont.createFont();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zone.setFontAndSize(policeDate, 10);
		zone.showTextAligned(Element.ALIGN_CENTER,
				numerotation,
				largeur/2,
				MARGE,
				0);
		zone.endText();		
	}

	public void genererCode(List<String> codes) {
		try {
			File newFile = new File(fichierSortie.getAbsolutePath() + "/qcmCodes.pdf");
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(newFile));
			document.open();
			zone = writer.getDirectContent();
			
			int nb = codes.size();
			
			for (int i = 0 ; i < nb; i++) {
				ecrireCode(codes.get(i));
				
				if (i < nb) {
					document.newPage();
				}
			}
			
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
			}
		document.close();
	}
	
	private void ecrireCode(String code) {
		try {
			document.add(new Paragraph("Code : \n\n"));
			
			Paragraph paraCode = new Paragraph(code);
			Font policeTitre = paraCode.getFont();
			policeTitre.setSize(24f);
			paraCode.setFont(new Font(Font.FontFamily.HELVETICA, 24f));
			paraCode.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paraCode);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
