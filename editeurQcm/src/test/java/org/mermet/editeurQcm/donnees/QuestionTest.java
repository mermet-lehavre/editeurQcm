package org.mermet.editeurQcm.donnees;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionTest {

    private static Question question;
    private static final String enonce = "ENONCE";

    private static Reponse reponse;
    private static final String PROPOSITION = "Est-ce vrai ?";
    private static final boolean CORRECT = true;

    @BeforeClass
    public void setUpClass() {
        question = new Question(enonce);
        reponse = new Reponse(PROPOSITION, CORRECT);
        question.addReponse(reponse);
    }

    @Test
    public void testGetPropositions() {
        List<Reponse> proposition = new ArrayList<>();
        proposition.add(reponse);
        assertEquals(proposition, question.getPropositions());

    }
}