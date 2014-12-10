package org.mermet.editeurQcm.interro.donnees;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class StructureQcm {
	private String titre;
	private String date;
	private String duree;
	private String avantPropos;
	private List<PartieStructure> parties;
	public static final String LISTE_PARTIES = "LISTE_PARTIES";
	private PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	public StructureQcm(String monTitre, String maDate, String maDuree, String monAvantPropos) {
		setTitre(monTitre);
		setDate(maDate);
		setDuree(maDuree);
		setAvantPropos(monAvantPropos);
		parties = new ArrayList<>();
	}
	
	public void addPartie(PartieStructure partie) {
		parties.add(partie);
		support.firePropertyChange(LISTE_PARTIES, null, partie);
	}
	
	public void removePartie(PartieStructure partie) {
		parties.remove(partie);
		support.firePropertyChange(LISTE_PARTIES, partie, null);
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getAvantPropos() {
		return avantPropos;
	}

	public void setAvantPropos(String avantPropos) {
		this.avantPropos = avantPropos;
	}
	
	public void addPropertyChangeListener(String prop, PropertyChangeListener lstn) {
		support.addPropertyChangeListener(prop, lstn);
	}

	public void removePropertyChangeListener(String prop, PropertyChangeListener lstn) {
		support.removePropertyChangeListener(prop, lstn);
	}
	
	public List<PartieStructure> getParties() {
		return parties;
	}
}
