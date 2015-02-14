<?php

require_once("../src/controllers/XMLTools.php");
require_once("../src/model/QCM.php");
require_once("../src/model/Etudiant.php");
require_once("../src/model/Partie.php");
require_once("../src/model/Question.php");
require_once("../src/model/Reponse.php");
 
/*
 * Traitement du code
 */

$xmlTools = new XMLTools();
$xmlTools->initQcmFile("../");
$xmlTools->setCode($_POST["code"]);
$qcm = $xmlTools->searchQCM();

if (!isset($qcm) || $qcm == NULL) {
    header("Location: index.php?err=null");
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
	<div class="panel panel-default" style="border-color:rgb(142, 134, 255);">
  <div class="panel-body">
  
		<div class="jumbotron" style="text-align: center;background-color: rgb(104, 96, 215);">
        <h1><?php echo $qcm->getTitre() ?></h1>
		</div>
		
    <form method="post" action="../src/controllers/submitQCM.php">
        
		
		<div class="row"><input class="form-control" placeholder="Nom" type="text" name="nom" required></div>
        <div class="row"><input class="form-control" placeholder="Prenom" type="text" name="prenom" required></div>
        <div class="row"><input class="form-control" placeholder="Numéro Etudiant" type="text" name="numEtudiant" required></div>
        <input type="hidden" name="code" value="<?php echo $qcm->getEtudiant()->getCode(); ?>"/>

        <h3><?php echo $qcm->getAvantPropos() ?></h3>
		
        <?php
        foreach ($qcm->getParties() as $partie) {
		echo"<div class='jumbotron' style='text-align: center;margin-top: 25px;background-color: darkturquoise;'>";
            echo "<p class='contenu'>" . $partie->getTitrePartie() . "</p>";
		echo"</div>";
            foreach ($partie->getQuestions() as $question) {
                $idQuestion = $question->getId();
                $enonce = $question->getEnonce();
                echo "<div style='text-align: center;margin-top: 25px;'>
				<div class='jumbotron' style='background-color: rgb(142, 134, 255);'>Question $idQuestion : $enonce</div></div><div>";
                foreach ($question->getReponses() as $reponse) {
                    $idReponse = $reponse->getId();
                    $proposition = $reponse->getProposition();
                    $idInput = "reponse" . $idReponse . "q" . $idQuestion;
					
                    echo "
					<div class='row'>
					<div class='col-lg-6'>
					<div class='input-group'>
					<span class='input-group-addon'>
					<input type='checkbox' name='question-" . $idQuestion . "[]' value='$idReponse' id='$idInput'/> 
					</span>
					<input type='text' disabled class='form-control' style='text-align: center;background: white;' 
					aria-label='Text input with checkbox' for='$idInput' value='$proposition'/> <br/>
					</div>
					</div>
					</div>
					";
                }
                echo "</div>";
            }
        }
        ?>
        <input type="submit" value="Envoyer" onclick="return confirm('Etes-vous sur de vouloir valider le QCM ?')"/>
    </form>

    <footer>
        <p>Mr Mermet</p>
    </footer>

</div>
<script src="../ressources/js/jquery-2.1.3.js"></script>
<script src="../ressources/js/bootstrap.js"></script>
</body>
</html>
