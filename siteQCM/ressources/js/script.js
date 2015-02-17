function afficherQCM(code) {
    $(".show-qcm").hide();
    $("#qcm-"+code).show();
    $("li").removeClass("active");
    $("#"+code).addClass("active");
}

$("#toTop").click(function () {
    //1 second of animation time
    //html works for FFX but not Chrome
    //body works for Chrome but not FFX
    //This strange selector seems to work universally
    $("html, body").animate({scrollTop: 0}, 1000);
});