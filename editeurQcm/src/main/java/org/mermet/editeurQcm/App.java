package org.mermet.editeurQcm;

import java.io.File;
import org.mermet.editeurQcm.donnees.Matiere;


public class App 
{
    public static void main(String[] args) {
        System.out.println("Lecture depuis un fichier");
/*        String nomHome = System.getProperty("user.home");
        File home = new File(nomHome);
        File dossierQuestions = new File(home,".qcm");
        File fic = new File(dossierQuestions,"questions.xml");*/
        File fic = new File("questions.xml");
        LecteurMatiere base = new LecteurMatiere(fic);
        Matiere exam = base.getExamen();
        System.out.println(exam);


        System.out.println("Lecture depuis une chaîne");
        LecteurMatiere base2 = new LecteurMatiere("<examen><nom-matiere>développement java web</nom-matiere><theme><titre>JSP simple</titre><question><enonce>Quelle balise permet d'insérer des instructions Java dans une JSP ?</enonce>" +
      "<reponse><proposition>&lt;% . . . %&gt;</proposition>    <correct>true</correct>      </reponse>      <reponse>  <proposition>&lt;? . . . ?&gt;</proposition>"+
        "<correct>false</correct>      </reponse>      <reponse>        <proposition>&lt;?java . . . %&gt;</proposition>        <correct>false</correct>"+
      "</reponse>      <reponse>        <proposition>&lt;java&gt; . . . &lt;/java&gt;</proposition>     <correct>false</correct>      </reponse>"+
      "<reponse>        <proposition>Aucune balise. Le code java est reconnu et exécuté automatiquement.</proposition>  <correct>false</correct>      </reponse>"+
      "    </question>    <question>      <enonce>Quel est le rôle de la balise &lt;%= . . . %&gt; ?</enonce>      <reponse>    <proposition>Afficher la valeur d'une expression java</proposition>     <correct>true</correct>      </reponse>      <reponse>  <proposition>Mettre une partie du code en commentaire</proposition>     <correct>false</correct>      </reponse>"+
      "      <reponse>  <proposition>Comparer 2 valeurs</proposition>   <correct>false</correct>      </reponse>      <reponse> <proposition>Effectuer un 'import' java</proposition>"+
      " <correct>false</correct>      </reponse>      <reponse> <proposition>Cette balise n'existe pas.</proposition>   <correct>false</correct>"+
      "      </reponse></question></theme></examen>");
        Matiere exam2 = base2.getExamen();
        System.out.println(exam2);

        System.out.println("Ecriture dans un fichier");
        File sortie = new File("essaiSortie.xml");
        EcrivainMatiere ecrivain = new EcrivainMatiere(sortie);
        String resultat = ecrivain.ecrire(exam);
        System.out.println("RESULTAT="+ resultat);
        
    }


}
