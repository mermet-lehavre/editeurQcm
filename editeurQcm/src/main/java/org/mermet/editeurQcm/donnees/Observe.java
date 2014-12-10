package org.mermet.editeurQcm.donnees;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Observe {

	protected PropertyChangeSupport support = new PropertyChangeSupport(this);

	public Observe() {
		super();
	}

	public void addPropertyChangeListener(String prop, PropertyChangeListener pcl) {
		support.addPropertyChangeListener(prop, pcl);
	}

	public void removePropertyChangeListener(String prop, PropertyChangeListener pcl) {
		support.removePropertyChangeListener(prop, pcl);
	}

}