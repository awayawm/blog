$(document).ready(function() {
    $("#logout").bind("click", function(event) {
        $.ajax({
            method: 'GET',
            url: '/logout'
        }).done(function(response) {
            console.log(response)
            window.location.replace ("/")
        })
    })
})