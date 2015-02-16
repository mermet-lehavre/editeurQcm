function afficherQCM(code) {
    $(".show-qcm").hide();
    $("#qcm-"+code).show();
    $("li").removeClass("active");
    $("#"+code).addClass("active");
}