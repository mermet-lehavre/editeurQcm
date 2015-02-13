package org.mermet.editeurQcm.ihm.fenetre;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import org.mermet.editeurQcm.ihm.actions.generales.ActionEditer;
import org.mermet.editeurQcm.ihm.actions.generales.ActionEffacer;
import org.mermet.editeurQcm.ihm.actions.generales.ActionEnregistrer;
import org.mermet.editeurQcm.ihm.actions.generales.ActionNouveau;
import org.mermet.editeurQcm.ihm.actions.generales.ActionOuvrir;
import org.mermet.editeurQcm.ihm.actions.interro.ActionCreerInterro;
import org.mermet.editeurQcm.ihm.actions.interro.ActionGenererDevoir;
import org.mermet.editeurQcm.ihm.actions.interro.ActionGenererSite;
import org.mermet.editeurQcm.ihm.actions.interro.ActionGenererXml;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame {
	private static FenetrePrincipale instance = null;
	
	private FenetrePrincipale() {
		super("Édition de la base de question");
		initMenus();
		setSize(600,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static FenetrePrincipale getInstance() {
		if (instance == null) {
			instance = new FenetrePrincipale();
		}
		return instance;
	}
	
	private void initMenus() {
		JMenuBar barre = new JMenuBar();
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem nouveau = new JMenuItem(ActionNouveau.getInstance());
		JMenuItem ouvrir = new JMenuItem(ActionOuvrir.getInstance());
		JMenuItem enregistrer = new JMenuItem(ActionEnregistrer.getInstance());
		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.addActionListener(ae -> System.exit(0));
		menuFichier.add(nouveau);
		menuFichier.add(ouvrir);
		menuFichier.add(enregistrer);
		menuFichier.addSeparator();
		menuFichier.add(quitter);
		barre.add(menuFichier);

		JMenu menuEditer = new JMenu("Éditer");
		JMenuItem effacer = new JMenuItem(ActionEffacer.getInstance());
		JMenuItem editer = new JMenuItem(ActionEditer.getInstance());
		menuEditer.add(effacer);
		menuEditer.add(editer);
		
		JMenu menuInterro = new JMenu("Interro");
		JMenuItem creerInterro = new JMenuItem(ActionCreerInterro.getInstance());
		menuInterro.add(creerInterro);
		JMenuItem creer1qcm = new JMenuItem(ActionGenererDevoir.getInstance());
		menuInterro.add(creer1qcm);
        JMenuItem genererXml = new JMenuItem(ActionGenererXml.getInstance());
        menuInterro.add(genererXml);
        JMenuItem genererSite = new JMenuItem(ActionGenererSite.getInstance());
        menuInterro.add(genererSite);
		
		barre.add(menuFichier);
		barre.add(menuEditer);
		barre.add(menuInterro);
		this.setJMenuBar(barre);
		
	}

	public static void main(String... args) {
		SwingUtilities.invokeLater(() -> new FenetrePrincipale());
	}

}
