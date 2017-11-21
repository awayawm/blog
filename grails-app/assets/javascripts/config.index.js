var getConfig = function() {
    $.ajax({
        url: 'config/get',
        method: 'get'
    }).done(function(res) {
        $("#title").val(res.title),
        $("#sitetitle").val(res.siteTitle),
        $("#tagline").val(res.tagline),
        $("#favicon").val(res.favicon),
        $("#recaptchakey").val(res.recaptchaKey),
        $("#analyticskey").val(res.analyticsKey),
        $("#enablecaptcha").attr("checked", res.enableCaptcha)

        $("option").each(function(i) {
            if($(this).val() == res.shortTokenTimer) {
                $(this).attr("selected", "selected")
            }
            if($(this).val() == res.longTokenTimer) {
                $(this).attr("selected", "selected")
            }
        })

    })
}

$(document).ready(function() {

$("#submit").bind("click", function(e) {
    var data = {
        title: $("#title").val(),
        sitetitle: $("#sitetitle").val(),
        tagline: $("#tagline").val(),
        favicon: $("#favicon").val(),
        shorttokentimer: $("#shorttokentimer").val(),
        longtokentimer: $("#longtokentimer").val(),
        analyticskey: $("#analyticskey").val(),
        recaptchakey: $("#recaptchakey").val(),
        enablecaptcha: $("#enablecaptcha").prop("checked")
    }

    $.ajax({
        url: 'config/submit',
        method: 'post',
        data: data
    }).done(function(res) {
        if(res) {
            getConfig()
        } else {
            console.log(res)
        }
    })
})
    getConfig()
})