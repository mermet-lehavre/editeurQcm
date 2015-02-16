<?php

session_start();

if (isset($_SESSION['password'])) {
    $password = $_SESSION['password'];
}
else {
    $password = $_POST['password'];
}

require_once("../src/controllers/XMLTools.php");

$xmlTools = new XMLTools();
$xmlTools->initAdminFile("../");

if ($xmlTools->checkPassword($password)) {
    header("Location: admin.php");
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
            <div class="jumbotron titre"><h2>Partie Administrateur</h2></div>
    <div class="bs-callout bs-callout-info" style="border-left-color: #1b809e;">
    <form class="form-qcm" action="admin.php" method="post">
        <input class="form-control" style="width: 100%;" placeholder="Saisir le mot de passe" type="password" name="password" required/>
        <input class="btn btn-primary btn-block" type="submit"/>

        <?php
        if (isset($_GET['pwd']) && $_GET['pwd'] == "wrong") {
            echo "<div class='alert alert-danger' role='alert'><i class='glyphicon glyphicon-remove-sign'></i> Mot de passe incorrect</div>";
        }
        ?>
    </form>
	</div>
	</div>
	</div>
	</div>

</body>
</html>
