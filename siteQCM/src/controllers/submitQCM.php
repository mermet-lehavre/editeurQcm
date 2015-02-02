<?php

require_once("XMLTools.php");
require_once("../model/QCM.php");
require_once("../model/Etudiant.php");
require_once("../model/Partie.php");
require_once("../model/Question.php");
require_once("../model/Reponse.php");

if (!isset($_POST['code']) || $_POST['code'] == NULL) {
    header("Location: index.php");
    die();
}

$xmlTools = new XMLTools("../../ressources/data/exemple.xml");
$xmlTools->setCode($_POST["code"]);
$qcm = $xmlTools->searchQCM();


if (!isset($qcm) || $qcm == NULL) {
    header("Location: index.php");
    die();
}

foreach ($_POST as $key => $value) {
    if (strpos($key, "question") !== false) {
        $questions = explode("-", $key);

        $idQuestion = $questions[1];
        $idReponse = $value;

        foreach ($qcm->getParties() as $partie) {
            $question = $partie->findQuestion($idQuestion);
            if (isset($question) && $question != NULL) {
                $reponse = $question->findReponse($idReponse);
                if (isset($reponse) && $reponse != NULL) {
                    $xmlTools->addReponse($question, $reponse);
                    echo $question->getEnonce() . "=>" .$reponse->getProposition() . "<br />";
                }
            }
        }
    }
}

$xmlTools->submitQCM();

?>