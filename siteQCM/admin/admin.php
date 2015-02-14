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

if (!$xmlTools->checkPassword($password)) {
    header("Location: index.php?pwd=wrong");
    die();
}

$_SESSION['password'] = $password;

$fichierQCM = $xmlTools->shearchQCMFile();

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
    <form class="form-qcm" action="../src/controllers/editFileQCM.php" method="post">
        <div class="panel panel-default" style="border-color:darkturquoise;">
		<div class="panel-body">
            <div class="jumbotron titre" style="background-color:darkturquoise;"><h5>Nom du Fichier QCM</h5></div>
        <input class="form-control" style="width:100%" type="text" name="fichierQCM" placeholder="fichier.xml" value="<?php echo $fichierQCM; ?>" Z required/>
        <input class="btn btn-primary btn-block" style="width:100%" type="submit"/>
		</div>
		</div>
    </form>
    <?php
    if (isset($_GET['success']) && $_GET['success'] == "edit") {
        echo "<div class='alert alert-success' role='alert'><i class='glyphicon glyphicon-ok-sign'></i> Fichier QCM édité</div>";
    }
    ?>
    <div class="form-qcm">
        <a class="btn btn-primary btn-lg" style="width:100%" href="listeQcm.php">Liste des QCMs remplis</a>
    </div>
	</div>
</div>
</div>

</body>
</html>