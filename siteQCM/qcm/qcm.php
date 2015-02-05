<?php

require_once("../src/controllers/XMLTools.php");
require_once("../src/model/QCM.php");
require_once("../src/model/Etudiant.php");
require_once("../src/model/Partie.php");
require_once("../src/model/Question.php");
require_once("../src/model/Reponse.php");

if (!isset($_POST["code"])) {
    header("Location: index.html");
    die();
}

/*
 * Traitement du code
 */

$xmlTools = new XMLTools("../ressources/data/exemple.xml");
$xmlTools->setCode($_POST["code"]);
$qcm = $xmlTools->searchQCM();

if (!isset($qcm) || $qcm == NULL) {
    header("Location: index.html");
    die();
}
?>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>QCM</title>
    <link rel="stylesheet" type="text/css" href="../ressources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../ressources/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="../ressources/css/style.css">
</head>
<body>

<div class="container">
    <form method="post" action="../src/controllers/submitQCM.php">
        <div class="row"><label>Nom</label><input type="text" name="nom" required></div>
        <div class="row"><label>Prenom</label><input type="text" name="prenom" required></div>
        <div class="row"><label>Numéro Etudiant</label><input type="text" name="numEtudiant" required></div>
        <h1><?php echo $qcm->getTitre() ?></h1>

        <input type="hidden" name="code" value="<?php echo $qcm->getEtudiant()->getCode(); ?>"/>

        <?php

        foreach ($qcm->getParties() as $partie) {
            echo "<p class='contenu'>" . $partie->getTitrePartie() . "</p>";

            foreach ($partie->getQuestions() as $question) {
                $idQuestion = $question->getId();
                $enonce = $question->getEnonce();
                echo "<div><legend>Question $idQuestion : </legend>$enonce</div><div>";
                foreach ($question->getReponses() as $reponse) {
                    $idReponse = $reponse->getId();
                    $proposition = $reponse->getProposition();
                    $idInput = "reponse" . $idReponse . "q" . $idQuestion;
                    echo "<input type='checkbox' name='question-" . $idQuestion . "[]' value='$idReponse' id='$idInput'/> <label for='$idInput'>$proposition</label> <br/>";
                }
                echo "</div>";
            }
        }
        ?>
        <input type="submit" value="Envoyer"/>
    </form>

    <footer>
        <p>Mr Mermet</p>
    </footer>

</div>
<script src="../ressources/js/jquery-2.1.3.js"></script>
<script src="../ressources/js/bootstrap.js"></script>
</body>
</html>