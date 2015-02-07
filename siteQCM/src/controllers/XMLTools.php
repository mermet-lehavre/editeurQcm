<?php

class XMLTools
{
    private $document_xml;
    private $fichier;
    private $code;
    private $interro;

    function __construct($fichier) {
        $this->document_xml = new DomDocument();
        $this->fichier = $fichier;

        $this->document_xml->load($this->fichier);
    }

    function setCode($code) {
        $this->code = $code;
    }

    function checkPassword($pwd) {
        $password = $this->document_xml->getElementsByTagName('password')->item(0)->nodeValue;
        if($pwd === $password)
            return true;
        return false;
    }

    function showQCM() {
        $qcms = array();

        $titre = $this->document_xml->getElementsByTagName('titre-interro')->item(0)->nodeValue;
        $date = $this->document_xml->getElementsByTagName('date')->item(0)->nodeValue;
        $duree = $this->document_xml->getElementsByTagName('duree')->item(0)->nodeValue;
        $avantPropos = $this->document_xml->getElementsByTagName('avant-propos')->item(0)->nodeValue;

        $indexQCM = 0;

        foreach ($this->document_xml->getElementsByTagName('qcm') as $qcm) {
            if($qcm->getElementsByTagName('termine')->item(0)->nodeValue == "true") {
                $nom = $qcm->getElementsByTagName('nom')->item(0)->nodeValue;
                $prenom = $qcm->getElementsByTagName('prenom')->item(0)->nodeValue;
                $note = $qcm->getElementsByTagName('note')->item(0)->nodeValue;
                $etudiant = new Etudiant($nom, $prenom, $note);

                $this->interro = new QCM($titre, $date, $duree, $avantPropos, $etudiant);
                $indexPartie = 0;

                foreach ($qcm->getElementsByTagName('partie') as $partie) {
                    $titrePartie = $partie->getElementsByTagName('titre')->item(0)->nodeValue;
                    $parties = new Partie($titrePartie);
                    $indexQuestion = 0;

                    foreach ($partie->getElementsByTagName('question') as $question) {
                        $enonce = $question->getElementsByTagName('enonce')->item(0)->nodeValue;
                        $idQuestion = $question->getAttribute('id');

                        $questions = new Question($idQuestion, $enonce);
                        $indexReponse = 0;

                        foreach ($question->getElementsByTagName('reponse') as $reponse) {
                            $proposition = $reponse->getElementsByTagName('proposition')->item(0)->nodeValue;
                            $correct = $reponse->getElementsByTagName('correct')->item(0)->nodeValue;
                            $choixEtudiant = $reponse->getElementsByTagName('choix-etudiant')->item(0)->nodeValue;
                            $idReponse = $reponse->getAttribute('id');

                            if($choixEtudiant == "true") {
                                $choix = true;
                            } else {
                                $choix = false;
                            }
                            $reponses = new Reponse($idReponse, $proposition, $correct, $choix);
                            $questions->addReponse($indexReponse++, $reponses);
                        }

                        $parties->addQuestion($indexQuestion++, $questions);
                    }

                    $this->interro->addPartie($indexPartie++, $parties);
                }

                $qcms[$indexQCM++] = $this->interro;
            }
        }

        return $qcms;
    }

    function searchQCM() {
        $titre = $this->document_xml->getElementsByTagName('titre-interro')->item(0)->nodeValue;
        $date = $this->document_xml->getElementsByTagName('date')->item(0)->nodeValue;
        $duree = $this->document_xml->getElementsByTagName('duree')->item(0)->nodeValue;
        $avantPropos = $this->document_xml->getElementsByTagName('avant-propos')->item(0)->nodeValue;

        foreach ($this->document_xml->getElementsByTagName('qcm') as $qcm) {
            $code = $qcm->getElementsByTagName('code')->item(0)->nodeValue;

            if ($code == $this->code) {
                if ($qcm->getElementsByTagName('termine')->item(0)->nodeValue != "true") {
                    $etudiant = new Etudiant($code);
                    $this->interro = new QCM($titre, $date, $duree, $avantPropos, $etudiant);
                    $indexPartie = 0;

                    foreach ($qcm->getElementsByTagName('partie') as $partie) {
                        $titrePartie = $partie->getElementsByTagName('titre')->item(0)->nodeValue;
                        $parties = new Partie($titrePartie);
                        $indexQuestion = 0;

                        foreach ($partie->getElementsByTagName('question') as $question) {
                            $enonce = $question->getElementsByTagName('enonce')->item(0)->nodeValue;
                            $idQuestion = $question->getAttribute('id');

                            $questions = new Question($idQuestion, $enonce);
                            $indexReponse = 0;

                            foreach ($question->getElementsByTagName('reponse') as $reponse) {
                                $proposition = $reponse->getElementsByTagName('proposition')->item(0)->nodeValue;
                                $correct = $reponse->getElementsByTagName('correct')->item(0)->nodeValue;
                                $idReponse = $reponse->getAttribute('id');

                                $reponses = new Reponse($idReponse, $proposition, $correct);
                                $questions->addReponse($indexReponse++, $reponses);
                            }

                            $parties->addQuestion($indexQuestion++, $questions);
                        }

                        $this->interro->addPartie($indexPartie++, $parties);
                    }
                }
                return $this->interro;
            }
        }
        return NULL;
    }

    function addReponse($question, $reponse) {
        foreach ($this->document_xml->getElementsByTagName('qcm') as $qcm) {
            $code = $qcm->getElementsByTagName('code')->item(0)->nodeValue;

            if ($code == $this->code) {
                foreach($qcm->getElementsByTagName('question') as $xmlQuestion) {
                    if ($xmlQuestion->getAttribute('id') == $question->getId()) {
                        foreach($xmlQuestion->getElementsByTagName('reponse') as $xmlReponse) {
                            if ($xmlReponse->getAttribute('id') == $reponse->getId()) {
                                $xmlReponse->getElementsByTagName('choix-etudiant')->item(0)->nodeValue = "true";
                                if($xmlReponse->getElementsByTagName('correct')->item(0)->nodeValue == "true") {
                                    $qcm->getElementsByTagName('note')->item(0)->nodeValue = $qcm->getElementsByTagName('note')->item(0)->nodeValue + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    function submitQCM($nom, $prenom, $numEtudiant) {
        foreach ($this->document_xml->getElementsByTagName('qcm') as $qcm) {
            $code = $qcm->getElementsByTagName('code')->item(0)->nodeValue;

            if ($code == $this->code) {
                $qcm->getElementsByTagName('termine')->item(0)->nodeValue = "true";
                $qcm->getElementsByTagName('nom')->item(0)->nodeValue = $nom;
                $qcm->getElementsByTagName('prenom')->item(0)->nodeValue = $prenom;
                $qcm->getElementsByTagName('num-etudiant')->item(0)->nodeValue = $numEtudiant;
            }
        }

        $this->document_xml->save($this->fichier);
        echo "SAUVEGARDE du fichier " . $this->fichier;
    }
}
