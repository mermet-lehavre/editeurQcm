<?php

require_once("XMLTools.php");
require_once("../model/QCM.php");
require_once("../model/Etudiant.php");
require_once("../model/Partie.php");
require_once("../model/Question.php");
require_once("../model/Reponse.php");

if (!isset($_POST['code']) || $_POST['code'] == NULL) {
    header("Location: ../../qcm/index.php?err=null");
    die();
}

$xmlTools = new XMLTools();
$xmlTools->initQcmFile("../../");
$xmlTools->setCode($_POST["code"]);
$qcm = $xmlTools->searchQCM();


if (!isset($qcm) || $qcm == NULL) {
    header("Location: ../../qcm/index.php?err=null");
    die();
}

foreach ($_POST as $key => $values) {
    if (strpos($key, "question") !== false) {
        $questions = explode("-", $key);

        $idQuestion = $questions[1];
        foreach ($values as $value) {
            $idReponse = $value;

            foreach ($qcm->getParties() as $partie) {
                $question = $partie->findQuestion($idQuestion);
                if (isset($question) && $question != NULL) {
                    $reponse = $question->findReponse($idReponse);
                    if (isset($reponse) && $reponse != NULL) {
                        $xmlTools->addReponse($question, $reponse);
                    }
                }
            }
        }
    }
}

$xmlTools->addNote();
$xmlTools->submitQCM($_POST['nom'], $_POST['prenom'], $_POST['numEtudiant']);

header("Location: ../../qcm/index.php?success=true");

?>