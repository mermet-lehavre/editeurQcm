package org.mermet.editeurQcm.interro.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.mermet.editeurQcm.donnees.Matiere;
import org.mermet.editeurQcm.interro.donnees.StructureQcm;
import org.mermet.editeurQcm.interro.donnees.SourceListeQuestions;

public class DialogueCreationPartieListeQuestions extends DialogueCreationPartie {
	private JPanel panneauGeneral;
	private JPanel panneauListeQuestions;
	private JButton ajouterQuestion;
	private List<PanelQuestion> listeQuestions;
	
	public DialogueCreationPartieListeQuestions(JDialog parent, Matiere matiere, StructureQcm qcm) {
		super(parent, matiere, qcm);
	}

	@Override
	public void init() {
		panneauGeneral = new JPanel();
		panneauGeneral.setLayout(new BorderLayout());
		
		/* liste des questions */
		listeQuestions = new ArrayList<>();
		panneauListeQuestions = new JPanel();
		panneauListeQuestions.setLayout(new BoxLayout(panneauListeQuestions, BoxLayout.Y_AXIS));
		JScrollPane defilementQuestions = new JScrollPane(panneauListeQuestions,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		defilementQuestions.setPreferredSize(new Dimension(600, 100));
		panneauGeneral.add(defilementQuestions,BorderLayout.CENTER);
		listeQuestions.add(new PanelQuestion(matiere));
		majPanelQuestions();
		
		
		/* panneau de commande */
		ajouterQuestion = new JButton("Ajouter une question");
		ajouterQuestion.addActionListener(
				ae -> {
					listeQuestions.add(new PanelQuestion(matiere));
					majPanelQuestions();
				});
		JPanel panneauCommandes = new JPanel();
		panneauCommandes.add(ajouterQuestion);
		panneauGeneral.add(panneauCommandes, BorderLayout.SOUTH);
		
		add(panneauGeneral, BorderLayout.CENTER);
	}

	private void majPanelQuestions() {
		panneauListeQuestions.removeAll();
		/*for (PanelQuestion question : listeQuestions) {
			panneauListeQuestions.add(question);
		}*/
		listeQuestions.stream().forEach(question -> panneauListeQuestions.add(question));
		panneauListeQuestions.updateUI();
		
	}

	@Override
	public void majSource() {
		SourceListeQuestions source = new SourceListeQuestions();
		/*for (PanelQuestion panel : listeQuestions) {
			source.addQuestion(panel.getQuestion());
		}*/
		listeQuestions.stream().
			forEach(panel -> source.addQuestion(panel.getQuestion()));
		partie.setSource(source);
	}

	@Override
	public boolean validerSpecifique() {
		return getNbQuestions() <= listeQuestions.size();
	}
}
