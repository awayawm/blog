$(document).ready(function() {

    var tagTable = []

    function updateTagTable() {
        $("#tagTable").empty()
        for (item in tagTable) {
            $("#tagTable").append(  "<tr>" +
                                    "<td id='id'>" + tagTable[item].id + "</td>" +
                                    "<td>" + tagTable[item].name + "</td>" +
                                    "<td>" + tagTable[item].description + "</td>" +
                                    "<td>" +
                                    "<ul class='list-inline'>" +
                                    "<li class='list-inline-item'><svg height='32' id='" + tagTable[item].id + "' class='deleteButton octicon octicon-x' viewBox='0 0 12 16' version='1.1' width='24' aria-hidden='true'><path fill-rule='evenodd' d='M7.48 8l3.75 3.75-1.48 1.48L6 9.48l-3.75 3.75-1.48-1.48L4.52 8 .77 4.25l1.48-1.48L6 6.52l3.75-3.75 1.48 1.48z'></path></svg></li>" +
                                    "<li class='list-inline-item'><svg height='32' id='" + tagTable[item].id + "' class='editButton octicon octicon-pencil' viewBox='0 0 14 16' version='1.1' width='28' aria-hidden='true'><path fill-rule='evenodd' d='M0 12v3h3l8-8-3-3-8 8zm3 2H1v-2h1v1h1v1zm10.3-9.3L12 6 9 3l1.3-1.3a.996.996 0 0 1 1.41 0l1.59 1.59c.39.39.39 1.02 0 1.41z'></path></svg></li>" +
                                    "</ul>" +
                                    "</td>" +
                                    "</tr>")
            }
    }

    function updateTagTableModelandTable() {
        $.ajax({
            method: 'GET',
            url: 'tags/getall'
            }).done(function(response) {
                tagTable = response.data.tags
                updateTagTable()
            }).fail(function(response) {
                console.log(response)
        })
    }

    function createTag() {
        $.ajax({
            method: 'POST',
            data: {
                name: $("#name").val(),
                description: $("#description").val(),
                id: $("#id").val()
            },
            url: 'tags/submit'
            }).done(function(response) {
                $("#tagForm")[0].reset()
                updateTagTableModelandTable()
            })
    }

    $("#submit").bind("click", function(event) {
        event.preventDefault()
        createTag()
    })

    updateTagTableModelandTable()
    $("#tagForm")[0].reset()

})

$(document).on('click', '.deleteButton', function(event) {
    var id = $(this).attr("id")
    $.ajax({
        url: '/admin/tags/remove',
        method: 'post',
        data: {id: id}
    }).done(function(response) {
        console.log(response)
        window.location.href = "/admin/tags"
    }).fail(function(response) {
        console.log(response)
    })
})

$(document).on('click', '.editButton', function(event) {
    var id = $(this).attr("id")
    $.ajax({
        url: '/admin/tags/gettag/' + id,
        method: 'get',
    }).done(function(response) {
        $("#name").val(response.data.tag.name)
        $("#description").val(response.data.tag.description)
        $("#id").val(response.data.tag.id)
    }).fail(function(response) {
        console.log(response)
    })
})