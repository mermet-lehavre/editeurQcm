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

$xmlTools = new XMLTools();
$existeQCM = $xmlTools->existeQCM("data/exemple.xml", $_POST["code"]);

if (!isset($existeQCM) || $existeQCM == NULL) {
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
    <h1><?php echo $existeQCM->getTitre() ?></h1>

    <form method="post" action="controllers/submitQCM.php">
        <input type="hidden" name="code" value="<?php echo $existeQCM->getEtudiant()->getCode(); ?>" />

        <?php

        foreach($existeQCM->getParties() as $partie) {
            echo "<p class='contenu'>" . $partie->getTitrePartie() . "</p>";

            $questions = $partie->getQuestions();
            for($i = 0; $i < sizeof($questions); $i++) {
                $question = $questions[$i];
                echo "<div><legend>Question " . ($i+1) . " : </legend>" . $question->getEnonce() . "</div><div>";

                $responses = $question->getReponses();
                for($j = 0; $j < sizeof($responses); $j++) {
                    $reponse = $responses[$j];
                    echo "<input type='radio' name='question-$j' value='reponse1q1' id='reponse1q1'/> <label for='reponse1q1'>" . $reponse->getProposition(). "</label> <br/>";
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