<?php

session_start();

if (!isset($_SESSION['password'])) {
    header("Location: index.php?pwd=wrong");
    die();
}

// traitement du password + affichage des qcm
require_once("../src/controllers/XMLTools.php");

$xmlTools = new XMLTools();
$xmlTools->initAdminFile("../");

if (!$xmlTools->checkPassword($_SESSION['password'])) {
    header("Location: index.php?pwd=wrong");
    die();
}

require_once("../src/model/QCM.php");
require_once("../src/model/Etudiant.php");
require_once("../src/model/Partie.php");
require_once("../src/model/Question.php");
require_once("../src/model/Reponse.php");

$xmlTools->initQcmFile("../");
$qcms = $xmlTools->showQCM();

?>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <title>Admin - QCM</title>
    <link rel="stylesheet" type="text/css" href="../ressources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../ressources/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="../ressources/css/style.css">
</head>
<body>
<div class="container">
    <div class="panel panel-default" style="border-color:rgb(142, 134, 255);">
        <div class="panel-body">
            <div class="jumbotron titre"><h2>Partie Administrateur</h2></div>

            <div class="row">
                <div class="col-md-4">
                    <?php if (sizeof($qcms) != 0) { ?>
                        <ul class="nav nav-pills nav-stacked">
                            <?php foreach ($qcms as $qcm) {
                                    $code = $qcm->getEtudiant()->getCode();
                            ?>
                                <li id="<?php echo $code; ?>" role="presentation"
                                    onclick='afficherQCM(<?php echo $code; ?>)'>
                                    <a href="#">
                                        <?php echo $qcm->getEtudiant()->getNom() . " " . $qcm->getEtudiant()->getPrenom() . " (" . $qcm->getEtudiant()->getNumero() . ")"; ?>
                                    </a>
                                </li>
                            <?php } ?>
                        </ul>
                    <?php } ?>
                </div>
                <div class="col-md-8">
                    <?php
                    foreach ($qcms as $qcm) {
                    ?>
                    <div class="show-qcm" hidden="true" id="qcm-<?php echo $qcm->getEtudiant()->getCode(); ?>">
                        <div><h3><?php echo $qcm->getEtudiant()->getNote(); ?>/20</h3>
                        <?php
                        foreach ($qcm->getParties() as $partie) {
                            echo "<div class='jumbotron titre-partie'>";
                            echo "<h3>" . $partie->getTitrePartie() . "</h3>";
                            echo "</div>";

                            foreach ($partie->getQuestions() as $question) {
                                echo "<div class='jumbotron enonce'>";
                                echo "<p class='contenu'>" . $question->getEnonce() . "</p>";
                                echo "</div>";

                                echo '<br/>';
                                foreach ($question->getReponses() as $reponse) {
                                    ?>
                                    <div class="input-group">
                                        <span class="input-group-addon"><?php
                                            if ($reponse->getChoixEtudiant() == "true") {
                                                echo '<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>';
                                            } else {
                                                echo '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>';
                                            }
                                            ?>
                                        </span>
                                        <button class='btn-admin btn <?php
                                            if ($reponse->getCorrect() == "false") {
                                                echo "btn-danger";
                                            }
                                            else {
                                                echo "btn-success";
                                            }
                                        ?>' type="text" disabled><xmp class="admin-proposition"><?php echo $reponse->getProposition(); ?></xmp></button>
                                    </div>

                                    <?php
                                }
                            }
                        }
                        ?>
                        <br/>
                        </div>
                    </div>
                    <?php } ?>
                </div>

                <script src="../ressources/js/script.js"></script>
                <script src="../ressources/js/jquery-2.1.3.js"></script>
                <script src="../ressources/js/bootstrap.js"></script>
            </div>
        </div>
</body>
</html>
