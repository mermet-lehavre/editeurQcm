package org.mermet.editeurQcm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mermet.editeurQcm.donnees.Question;
import org.mermet.editeurQcm.donnees.Reponse;
import org.mermet.editeurQcm.interro.donnees.Qcm;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class EssaiGenerationPdf {
	private String titre = "Titre du QCM";
	private String date  = "11/07/14";
	private String durée = "durée : 15 min";
	private String preambule = "Voici un long texte qui va me servir à vérifier le "+
			"fonctionnement d'un bloc 'paragraph' quand celui-ci est visiblement bien "+
			"trop long pour tenir sur une seule ligne dans le fichier pdf produit "+
			"et dépasser 2 lignes.";
	private int largeur;
	private int hauteur;
	private int nbPages;
	private Reponse reponse1;
	private Reponse reponse2;
	private Reponse reponse3;
	private Question question;
	private static final int MARGE = 20;
	private static final float INDENTATION_REPONSE = 30;
	private static final float ESPACE_AVANT_QUESTION = 10;
	private Document document;
	private PdfContentByte zone;

	public EssaiGenerationPdf() {
		document = new Document(PageSize.A4);
		largeur = (int) document.getPageSize().getWidth();
		hauteur = (int) document.getPageSize().getHeight();
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("testQcm.pdf"));
			document.open();

/*			PdfPTable enTete = new PdfPTable(2);
			PdfPCell cellDate = new PdfPCell();
			Paragraph pDate = new Paragraph("date");
			cellDate.addElement(pDate);
			pDate.setAlignment(Paragraph.ALIGN_LEFT);
			cellDate.setBorder(0);
			PdfPCell cellDuree = new PdfPCell();
			Paragraph pDuree = new Paragraph("duree");
			cellDuree.addElement(pDuree);
			cellDuree.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
//			pDuree.setAlignment(Paragraph.ALIGN_RIGHT);
			cellDuree.setBorder(0);
			enTete.addCell(cellDate);
			enTete.addCell(cellDuree);
			document.add(enTete);*/
			
			zone = writer.getDirectContent();
			
			genererNcopies(2);
			
			} catch (DocumentException de) {
				de.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		document.close();
	}

	private void genererNcopies(int nb) {
		for (int i = 1 ; i <= nb; i++) {
			generer1copie();
			if (i < nb) {
				document.newPage();
			}
		}
	}
	private void generer1copie() {
		preambule();
		reponse1 = new Reponse ("Premier énoncé de réponse", true);
		reponse2 = new Reponse ("Énoncé de la deuxième réponse ; il s'agit volontairement d'un exemple avec un texte ne tenant pas sur une seule ligne", false);
		reponse3 = new Reponse ("Énoncé de la troisième réponse", true);
		question = new Question("Ceci est le texte de la question");
		question.addReponse(reponse1);
		question.addReponse(reponse2);
		question.addReponse(reponse3);

		List<Question> liste = new ArrayList<>();
		for (int i = 0;  i < 10; i++) {
			liste.add(question);
		}
		nbPages = (liste.size()) / 4 + 1;
		afficherQuestions(liste);
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
				date,
				MARGE,
				hauteur-MARGE-10,
				0);
		zone.showTextAligned(Element.ALIGN_RIGHT,
				durée,
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
	
	private void preambule() {
		try {
			document.add(new Paragraph("\n\n"));
			
			Paragraph paraTitre = new Paragraph(titre);
			Font policeTitre = paraTitre.getFont();
			policeTitre.setSize(24f);
			paraTitre.setFont(new Font(Font.FontFamily.HELVETICA, 24f));
			paraTitre.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paraTitre);

			document.add(new Paragraph("\n\n"));
			
			Paragraph paraPreambule = new Paragraph(preambule);
			paraPreambule.setFont(new Font(Font.FontFamily.HELVETICA, 12f));
			paraPreambule.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			document.add(paraPreambule);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void piedPage(int numPage) {
		String numerotation = ""+numPage+"/"+nbPages;
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
	
	private void changerPage(int numPage) {
		document.newPage();
		enTetePage();
		piedPage(numPage);

	}

	private void afficherQuestions(List<Question> questions) {
		int compteurPage = 1;
		enTetePage();
		piedPage(compteurPage);
		for(int i = 0; i < questions.size(); i++) {
			if (i % 4 == 3) {
				compteurPage++;
				changerPage(compteurPage);
			}
			afficherQuestion(questions.get(i), i+1);
		}
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
				//char caseACocher = 'O';//'\u2126';
				String caseACocher = "O ";
				System.out.println(caseACocher);
				Chunk ccc = new Chunk(caseACocher);
				ccc.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14f));
				//ccc.setFont(new Font(BaseFont.createFont(BaseFont.TIMES_ROMAN, "#full", true)));
				Paragraph paraReponse = new Paragraph();
				paraReponse.add(ccc);
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
	
/*	private void afficherQuestion(Document document, Question question, int num) {
		try {
			Paragraph paraEnonce = new Paragraph(question.getEnonce());
			paraEnonce.setSpacingBefore(ESPACE_AVANT_QUESTION);
			document.add(paraEnonce);
			List<Reponse> reponses = question.getPropositionsAleatoires();
			for (Reponse reponse : reponses) {
				Paragraph paraReponse = new Paragraph(reponse.getProposition());
				paraReponse.setIndentationLeft(INDENTATION_REPONSE);
				paraReponse.setFirstLineIndent(-10);
				document.add(paraReponse);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public static void main(String... args) {
		new EssaiGenerationPdf();
	}
}
