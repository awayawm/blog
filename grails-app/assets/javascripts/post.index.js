$(document).ready(function() {

    $("#submit").bind("click", function(e) {

        var data = {
            title: $("#title").val(),
            link: $("#link").val(),
            summary: $("#summary").val(),
            content: $("#content").val(),
            enabled: $("#enabled").prop("checked")
        }

        $.ajax({
            url: '/admin/posts/edit',
            data: data,
            method: 'post'
        }).done(function(res) {
            console.log(res)
        })
    })
})