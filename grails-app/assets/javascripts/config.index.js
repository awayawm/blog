var getConfig = function() {
    $.ajax({
        url: 'config/get',
        method: 'get'
    }).done(function(res) {
        $("#title").val(res.title),
        $("#tagline").val(res.tagline),
        $("#favicon").val(res.favicon),
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
        tagline: $("#tagline").val(),
        favicon: $("#favicon").val(),
        shorttokentimer: $("#shorttokentimer").val(),
        longtokentimer: $("#longtokentimer").val(),
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