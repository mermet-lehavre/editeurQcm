<?php

session_start();

if (!isset($_SESSION['password'])) {
    header("Location: index.php?pwd=wrong");
    die();
}

// traitement du password + affichage des qcm
require_once("XMLTools.php");

$xmlTools = new XMLTools();
$xmlTools->initAdminFile("../../");

if(!$xmlTools->checkPassword($_SESSION['password'])) {
    header("Location: index.php?pwd=wrong");
    die();
}


$xmlTools->editFileQcm($_POST['fichierQCM']);

header("Location: ../../admin/admin.php?success=edit");

?>