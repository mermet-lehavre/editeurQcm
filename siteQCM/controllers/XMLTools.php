<?php

class XMLTools
{
    private $document_xml;

    function __construct()
    {
        $this->document_xml = new DomDocument();
    }

    function existeQCM($fichier, $codeQCM)
    {
        if (isset($fichier)) {
            $this->document_xml->load($fichier);

            $titre = $this->document_xml->getElementsByTagName('titre-interro')->item(0)->nodeValue;
            $date = $this->document_xml->getElementsByTagName('date')->item(0)->nodeValue;
            $duree = $this->document_xml->getElementsByTagName('duree')->item(0)->nodeValue;
            $avantPropos = $this->document_xml->getElementsByTagName('avant-propos')->item(0)->nodeValue;

            foreach ($this->document_xml->getElementsByTagName('qcm') as $qcm) {
                $code = $qcm->getElementsByTagName('code')->item(0)->nodeValue;

                if ($code === $codeQCM) {
                    $etudiant = new Etudiant($code);
                    $interro = new QCM($titre, $date, $duree, $avantPropos, $etudiant);
                    $indexPartie = 0;

                    foreach ($qcm->getElementsByTagName('partie') as $partie) {
                        $titrePartie = $partie->getElementsByTagName('titre')->item(0)->nodeValue;
                        $parties = new Partie($titrePartie);
                        $indexQuestion = 0;

                        foreach ($partie->getElementsByTagName('question') as $question) {
                            $enonce = $question->getElementsByTagName('enonce')->item(0)->nodeValue;
                            $questions = new Question($enonce);
                            $indexReponse = 0;

                            foreach ($question->getElementsByTagName('reponse') as $reponse) {
                                $proposition = $reponse->getElementsByTagName('proposition')->item(0)->nodeValue;
                                $correct = $reponse->getElementsByTagName('correct')->item(0)->nodeValue;

                                $reponses = new Reponse($proposition, $correct);
                                $questions->addReponse($indexReponse++, $reponses);
                            }

                            $parties->addQuestion($indexQuestion++, $questions);
                        }

                        $interro->addPartie($indexPartie++, $parties);
                    }
                    return $interro;
                }
            }
        }
        return NULL;
    }

}