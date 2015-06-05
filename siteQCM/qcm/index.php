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
    <form class="form-qcm" action="qcm.php" method="post">
        <h2 class="form-qcm-heading">Sélectionner un code</h2>
        <input class="form-control" style="width: 100%;" name="code" value="" required/>
        <input class="btn btn-primary btn-block" type="submit"/>

    <?php
    if (isset($_GET['err']) && $_GET['err'] == "null") {
        echo "<div class='alert alert-danger' role='alert'><i class='glyphicon glyphicon-remove-sign'></i> QCM introuvable ou déjà validé</div>";
    }
    if (isset($_GET['success']) && $_GET['success'] == "true") {
        echo "<div class='alert alert-success' role='alert'><i class='glyphicon glyphicon-ok-sign'></i> QCM validé</div>";
    }
    if (isset($_GET['fail']) && $_GET['fail'] == 'file') {
        echo "<div class='alert alert-danger' role='alert'><i class='glyphicon glyphicon-remove-sign'></i> Chargement impossible du fichier</div>";
    }
    ?>
    </form>
</div>
<div class="footer">
    <div class="container">M. Mermet - 2015</div>
</div>
</body>
</html>