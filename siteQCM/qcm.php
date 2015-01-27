<?php

require_once("controllers/XMLTools.php");
require_once("controllers/Etudiant.php");

if (!isset($_POST["code"])) {
    header("Location: index.php");
    die();
}

/*
 * Traitement du code
 */

$xmlTools = new XMLTools();
$etudiant = $xmlTools->existeQCM("data/exemple.xml", $_POST["code"]);

if (!isset($etudiant) || $etudiant == NULL) {
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
    <div class="header">
    <p>
        <strong>Date : 25/01/2015 </strong><br/>
        Nom : Bendjedid <br/>
        Prénom : Hana</p>
    </div>

    <h1> Titre du QCM </h1>

    <form method="post" action="controllers/submitQCM.php">
        <input type="hidden" name="code" value="<?php echo $etudiant->getCode(); ?>" />
        <!--

            Remplacer par une boucle
            lorsque le fichier XML sera près

        -->
        <p class="contenu"> Contenu de la question <strong></p>
        <fieldset>
            <legend>Question 1 :</legend>
            Ennoncé de la question ?<br/>
            <input type="radio" name="question-1" value="reponse1q1" id="reponse1q1"/> <label for="reponse1q1">La réponse
                1</label> <br/>
            <input type="radio" name="question-1" value="reponse2q1" id="reponse2q1"/> <label for="reponse2q1">La réponse
                2</label> <br/>
            <input type="radio" name="question-1" value="reponse3q1" id="reponse3q1"/> <label for="reponse3q1">La réponse
                3</label> <br/>
            <input type="radio" name="question-1" value="reponse4q1" id="reponse4q1"/> <label for="reponse4q1">La réponse
                4</label>
        </fieldset>
        <fieldset>
            <legend>Question 2 :</legend>
            Ennoncé de la question ?<br/>
            <input type="radio" name="question-2" value="reponse1q2" id="reponse1q2"/> <label for="reponse1q2">La réponse
                1</label> <br/>
            <input type="radio" name="question-2" value="reponse2q2" id="reponse2q2"/> <label for="reponse2q2">La réponse
                2</label> <br/>
            <input type="radio" name="question-2" value="reponse3q2" id="reponse3q2"/> <label for="reponse3q2">La réponse
                3</label> <br/>
            <input type="radio" name="question-2" value="reponse4q2" id="reponse4q2"/> <label for="reponse4q2">La réponse
                4</label>
        </fieldset>
        <fieldset>
            <legend>Question 3 :</legend>
            Ennoncé de la question ?<br/>
            <input type="radio" name="question-3" value="reponse1q3" id="reponse1q3"/> <label for="reponse1q3">La réponse
                1</label> <br/>
            <input type="radio" name="question-3" value="reponse2q3" id="reponse2q3"/> <label for="reponse2q3">La réponse
                2</label> <br/>
            <input type="radio" name="question-3" value="reponse3q3" id="reponse3q3"/> <label for="reponse3q3">La réponse
                3</label> <br/>
            <input type="radio" name="question-3" value="reponse4q3" id="reponse4q3"/> <label for="reponse4q3">La réponse
                4</label>
        </fieldset>
        <fieldset>
            <legend>Question 4 :</legend>
            Ennoncé de la question ?<br/>
            <input type="radio" name="question-4" value="reponse1q4" id="reponse1q4"/> <label for="reponse1q4">La réponse
                1</label> <br/>
            <input type="radio" name="question-4" value="reponse2q4" id="reponse2q4"/> <label for="reponse2q4">La réponse
                2</label> <br/>
            <input type="radio" name="question-4" value="reponse3q4" id="reponse3q4"/> <label for="reponse3q4">La réponse
                3</label> <br/>
            <input type="radio" name="question-4" value="reponse4q4" id="reponse4q4"/> <label for="reponse4q4">La réponse
                4</label>
        </fieldset>
        <fieldset>
            <legend>Question 5 :</legend>
            Ennoncé de la question ?<br/>
            <input type="radio" name="question-5" value="reponse1q5" id="reponse1q5"/> <label for="reponse1q5">La réponse
                1</label> <br/>
            <input type="radio" name="question-5" value="reponse2q5" id="reponse2q5"/> <label for="reponse2q5">La réponse
                2</label> <br/>
            <input type="radio" name="question-5" value="reponse3q5" id="reponse3q5"/> <label for="reponse3q5">La réponse
                3</label> <br/>
            <input type="radio" name="question-5" value="reponse4q5" id="reponse4q5"/> <label for="reponse4q5">La réponse
                4</label>
        </fieldset>
        <br/>
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