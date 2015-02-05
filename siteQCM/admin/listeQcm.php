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
<script src="../ressources/js/jquery-2.1.3.js"></script>
<script src="../ressources/js/bootstrap.js"></script>
</body>
</html>
