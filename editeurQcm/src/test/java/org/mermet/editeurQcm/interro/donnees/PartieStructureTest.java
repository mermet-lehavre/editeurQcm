package org.mermet.editeurQcm.interro.donnees;

import org.junit.*;
import static org.junit.Assert.*;

public class PartieStructureTest {

    private static PartieStructure ps;
    private static final String TITRE = "test";
    private static final int NB_QUESTION = 2;

    @BeforeClass
    public void setUpClass() {
        ps = new PartieStructure(TITRE, NB_QUESTION);
    }
    @Test
    public void testSetNbQuestions() {
        int newNbQuestion = 5;
        ps.setNbQuestions(newNbQuestion);
        assertEquals(newNbQuestion, ps.getNbQuestions());
    }

    @Test
    public void testSetTitre() {
        String newTitre = "nouveau titre";
        ps.setTitre(newTitre);
        assertEquals(newTitre, ps.getTitre());
    }

    @Test
    public void testGetNbQuestion() {
        assertEquals(NB_QUESTION, ps.getNbQuestions());
    }

    @Test
    public void testGetTitre() {
        assertEquals(TITRE, ps.getTitre());
    }

    @Test
    public void testGetResume() {
        assertFalse(true);
    }

    @Test
    public void testGenereQuestions() {
        assertFalse(true);
    }
}