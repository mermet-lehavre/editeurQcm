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
    
    <form class="form-qcm" action="admin.php" method="post">
        <h2 class="form-qcm-heading">Saisir le mot de passe</h2>
        <input class="form-control" type="password" name="password" required/>
        <input class="btn btn-primary btn-block" type="submit"/>

        <?php
        if (isset($_GET['pwd']) && $_GET['pwd'] == "wrong") {
            echo "<div class='alert alert-danger' role='alert'><i class='glyphicon glyphicon-remove-sign'></i> Mot de passe incorrect</div>";
        }
        ?>
    </form>
</div>

</body>
</html>
