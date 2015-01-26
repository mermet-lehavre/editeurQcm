<?php

if (!isset($_POST['code'])) {
    header("Location: ../qcm.php");
    die();
}


foreach ($_POST as $key => $value) {

    if (strpos($key, "code") !== false) {
        echo "CODE : " . $value . "<br />";
    }
    if (strpos($key, "question") !== false) {
        $questions = explode("-",$key);

        $idQuestion = $questions[1];
        $reponse = $value;

        // ajouter dans le XML
        


    }
}


?>