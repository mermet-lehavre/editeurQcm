<?php

require_once("controllers/XMLTools.php");
require_once("model/QCM.php");
require_once("model/Etudiant.php");
require_once("model/Partie.php");
require_once("model/Question.php");
require_once("model/Reponse.php");

if (!isset($_POST["code"])) {
    header("Location: index.php");
    die();
}

/*
 * Traitement du code
 */

$xmlTools = new XMLTools("data/exemple.xml", $_POST["code"]);
$qcm = $xmlTools->searchQCM();

if (!isset($qcm) || $qcm == NULL) {
    header("Location: index.php");
    die();
}
?>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>QCM</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<div class="container">
    <h1><?php echo $qcm->getTitre() ?></h1>

    <form method="post" action="controllers/submitQCM.php">
        <input type="hidden" name="code" value="<?php echo $qcm->getEtudiant()->getCode(); ?>" />

        <?php

        foreach($qcm->getParties() as $partie) {
            echo "<p class='contenu'>" . $partie->getTitrePartie() . "</p>";

            foreach ($partie->getQuestions() as $question) {
                $idQuestion = $question->getId();
                $enonce = $question->getEnonce();
                echo "<div><legend>Question $idQuestion : </legend>$enonce</div><div>";
                foreach ($question->getReponses() as $reponse) {
                    $idReponse = $reponse->getId();
                    $proposition = $reponse->getProposition();
                    $idInput = "reponse" .$idReponse ."q" . $idQuestion;
                    echo "<input type='radio' name='question-" . $idQuestion . "' value='$idReponse' id='$idInput'/> <label for='$idInput'>$proposition</label> <br/>";
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
    <script src="js/jquery-2.1.3.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>