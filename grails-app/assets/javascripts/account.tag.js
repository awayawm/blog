$(document).ready(function() {

    $("#submit").bind("click", function(event) {
        event.preventDefault()

        $.ajax({
            method: 'post',
            data: {
                name: $("#name").val(),
                description: $("#description").val()
            },
            url: 'tags/create'
            }).done(function(response) {
                console.log(response)
            }).fail(function(response) {
                console.log(response)
            })

        })
})