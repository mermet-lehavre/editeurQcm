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
    <h2 class="form-qcm-heading">Partie Administrateur</h2>

    <form class="form-qcm" action="../src/controllers/editFileQCM.php" method="post">
        <h3>Nom du Fichier QCM</h3>
        <input class="form-control" type="text" name="fichierQCM" value="<?php echo $fichierQCM; ?>" Z required/>
        <input class="btn btn-primary btn-block" type="submit"/>
    </form>
    <?php
    if (isset($_GET['success']) && $_GET['success'] == "edit") {
        echo "<div class='alert alert-success' role='alert'><i class='glyphicon glyphicon-ok-sign'></i> Fichier QCM édité</div>";
    }
    ?>
    <div class="form-qcm">
        <a class="btn btn-primary btn-lg" href="listeQcm.php">Liste des QCMs remplis</a>
    </div>
</div>

</body>
</html>