package org.mermet.editeurQcm.donnees;

import org.junit.*;
import static org.junit.Assert.*;

public class ReponseTest {

    private static Reponse reponse;
    private static final String PROPOSITION = "Est-ce vrai ?";
    private static final boolean CORRECT = true;

    @BeforeClass
    public void setUpClass() {
        reponse = new Reponse(PROPOSITION, CORRECT);
    }

    @Test
    public void testGetNumero() {
        assertEquals(-1, reponse.getNumero());
    }

    @Test
    public void testSetNumero() {
        int newNumero = 2;
        reponse.setNumero(newNumero);
        assertEquals(newNumero, reponse.getNumero());
    }

    @Test
    public void testGetProposition() {
        assertEquals(PROPOSITION, reponse.getProposition());
    }

    @Test
    public void testSetProposition() {
        String newProposition = "nouvelle proposition";
        reponse.setProposition(newProposition);
        assertEquals(newProposition, reponse.getProposition());
    }

    @Test
    public void testIsCorrect() {
        assertEquals(CORRECT, reponse.isCorrect());
    }

    @Test
    public void testSetCorrect() {
        boolean newCorrect = !CORRECT;
        reponse.setCorrect(newCorrect);
        assertEquals(newCorrect, reponse.isCorrect());
    }
}