package org.mermet.editeurQcm.interro.donnees;

import java.util.List;

public interface IQcm {

	public String toString();

	public String getTitre();

	public List<PartieQcm> getListeParties();

}