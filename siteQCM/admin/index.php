<?php
    if (isset($_GET['pwd'])) {
        if($_GET['pwd'] == "wrong") {
            $msg = "Mauvais mot de passe.";
        } else {
            $msg = "Veuillez entrer un mot de passe.";
        }
    } else {
        $msg = null;
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
    <?php if(isset($msg)) echo ($msg);?>
    <form action="listeQcm.php" method="post">
        <label>password</label>
        <input type="password" name="password" value="" />
        <input type="submit" />
    </form>
</div>

</body>
</html>
