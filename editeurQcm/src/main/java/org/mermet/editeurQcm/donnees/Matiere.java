package org.mermet.editeurQcm.donnees;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.jdom2.Element;

/**                                                                                                                                                                    
 *                                                                                                                                                                     
 * @author brunomermet                                                                                                                                                 
 */
public class Matiere extends ObjetMetier implements Collection<Theme> {
    public static final String TITRE = "TITRE";
	public static final String LISTE_THEMES = "lISTE_THEMES";
	private final transient List<Theme> themes;
    private transient String titre;
    @Override
    public int size() {
        return themes.size();
    }

    @Override
    public boolean isEmpty() {
        return themes.isEmpty();
    }

    @Override
    public boolean contains(final Object obj) {
        return themes.contains(obj);
    }

    @Override
    public Iterator<Theme> iterator() {
        return themes.iterator();
    }

    @Override
    public Object[] toArray() {
        return themes.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] tab) {
        return themes.toArray(tab);
    }

    @Override
    public boolean add(final Theme ite) {
    	boolean retour = themes.add(ite);
    	support.firePropertyChange(LISTE_THEMES, null, ite);
    	return retour;
    }

    @Override
    public boolean remove(final Object obj) {
        boolean retour = themes.remove(obj);
    	support.firePropertyChange(LISTE_THEMES, obj, null);
    	return retour;
    }

    @Override
    public boolean containsAll(final Collection<?> clctn) {
        return themes.containsAll(clctn);
    }

    @Override
    public boolean addAll(final Collection<? extends Theme> clctn) {
        boolean retour = themes.addAll(clctn);
    	support.firePropertyChange(LISTE_THEMES, null, clctn);
    	return retour;
    }

    @Override
    public boolean removeAll(final Collection<?> clctn) {
        boolean retour = themes.removeAll(clctn);
    	support.firePropertyChange(LISTE_THEMES, clctn, null);
    	return retour;
    }

    @Override
    public boolean retainAll(final Collection<?> clctn) {
        boolean retour = themes.retainAll(clctn);
    	support.firePropertyChange(LISTE_THEMES, null, clctn);
    	return retour;
    }

    @Override
    public void clear() {
        themes.clear();
    	support.firePropertyChange(LISTE_THEMES, null, null);
    }

    public Matiere(final String monTitre) {
        titre = monTitre;
        themes = new ArrayList<Theme>();
    }

    public Theme getTheme(final int indice) {
        return themes.get(indice);
    }

    @Override
        public String toString() {
        StringBuilder retour = new StringBuilder("==== Examen " + titre + " ====\n");

        themes.stream().forEach(
        		theme -> retour
        			.append("  ")
        			.append(theme.toString())
        			.append("\n"));
        return retour.toString();
    }
    
    @Override
    public Element toXML() {
    	Element retour = new Element("matiere");
    	Element nom = new Element("nom-matiere");
    	nom.addContent(titre);
    	retour.addContent(nom);
    	themes.stream().forEach(theme -> retour.addContent(theme.toXML()));
    	return retour;
    }
    
    public Matiere(Element eltMatiere) {
    	Element eltTitre = eltMatiere.getChild("nom-matiere");
    	titre = eltTitre.getText();
    	List<Element> eltThemes = eltMatiere.getChildren("theme");
		themes = eltThemes
				.stream()
				.map(x->new Theme(x))
				.collect(Collectors.toList());
    	/*themes = new ArrayList<>();
		for (Element eltTheme : eltThemes) {
			Theme theme = new Theme(eltTheme);
			themes.add(theme);
		}*/
    }
    
    public String getTitre() {
    	return titre;
    }
    
    public void setTitre(String monTitre) {
    	String ancienTitre = titre;
    	titre = monTitre;
    	support.firePropertyChange(TITRE, ancienTitre, titre);
    }
    
}
