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
    header("Location: ../../admin/index.php?pwd=wrong");
    die();
}

if(!$xmlTools->checkPassword($_POST['oldPassword'])) {
    header("Location: ../../admin/admin.php?fail=password");
    die();
}

$xmlTools->editPassword($_POST['newPassword']);
$_SESSION['password'] = $_POST['newPassword'];

header("Location: ../../admin/admin.php?success=password");

?>