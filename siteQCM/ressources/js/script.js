function afficherQCM() {
    $(".show-qcm").hide();
    $("#qcm-<?php echo $qcm->getEtudiant()->getCode(); ?>").show();
    $("li").removeClass("active");
    $("#<?php echo $qcm->getEtudiant()->getCode(); ?>").addClass("active");
}