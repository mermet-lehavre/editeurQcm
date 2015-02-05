<?php
// traitement du password + affichage des qcm
require_once("../src/controllers/XMLTools.php");

if (!isset($_POST['password']) || $_POST['password'] == NULL) {
    header("Location: index.php?pwd=empty");
    die();
}

$xmlTools = new XMLTools("../ressources/data/admin.xml");

if(!$xmlTools->checkPassword($_POST['password'])) {
    header("Location: index.php?pwd=wrong");
    die();
}

require_once("../src/model/QCM.php");
require_once("../src/model/Etudiant.php");
require_once("../src/model/Partie.php");
require_once("../src/model/Question.php");
require_once("../src/model/Reponse.php");

$xmlTools = new XMLTools("../ressources/data/exemple.xml");

$qcms = $xmlTools->showQCM();

?>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Admin - QCM</title>
    <link rel="stylesheet" type="text/css" href="../ressources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../ressources/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="../ressources/css/style.css">
</head>
<body>
    <h3>Page administrateur</h3>
    <?php
        foreach($qcms as $qcm) {
            echo $qcm->getEtudiant()->getNom();
            echo '<br/>';
            echo $qcm->getEtudiant()->getPrenom();
            echo '<br/>';
            echo $qcm->getEtudiant()->getNote();
            echo '<br/>';

            foreach($qcm->getParties() as $partie) {
                echo $partie->getTitrePartie();
                echo '<br/>';
                foreach($partie->getQuestions() as $question) {
                    echo $question->getEnonce();
                    echo '<br/>';
                    foreach($question->getReponses() as $reponse) {
                        if($reponse->getChoixEtudiant() == "true") {
                            echo '<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>';
                        } else {
                            echo '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>';
                        }
                        if($reponse->getCorrect() == "false") {
                            echo '<del>';
                        }
                        echo $reponse->getProposition();
                        echo '</del>';
                        echo '<br/>';
                    }
                }
            }
            echo '<br/>';
        }
    ?>
<script src="../ressources/js/jquery-2.1.3.js"></script>
<script src="../ressources/js/bootstrap.js"></script>
</body>
</html>
