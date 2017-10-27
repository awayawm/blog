$(document).ready(function() {

    var tagTable = []

    function updateTagTable() {
        $("#tagTable").empty()
        for (item in tagTable) {
        console.log(tagTable[item])
        $("#tagTable").append(  "<tr>" +
                                "<td id='id'>" + tagTable[item].id + "</td>" +
                                "<td>" + tagTable[item].name + "</td>" +
                                "<td>" + tagTable[item].description + "</td>" +
                                "<td>" +
                                "<svg height='32'  id='" + tagTable[item].id + "' class='deleteButton octicon octicon-x' viewBox='0 0 12 16' version='1.1' width='24' aria-hidden='true'><path fill-rule='evenodd' d='M7.48 8l3.75 3.75-1.48 1.48L6 9.48l-3.75 3.75-1.48-1.48L4.52 8 .77 4.25l1.48-1.48L6 6.52l3.75-3.75 1.48 1.48z'></path></svg>" +
                                "</td>" +
                                "</tr>")
        }
    }

    function updateTagTableModelandTable() {
        $.ajax({
            method: 'get',
            url: 'tags/getall'
            }).done(function(response) {
                tagTable = response.data
                updateTagTable()
            }).fail(function(response) {
                console.log(response)
        })
    }

    function createTag() {
        $.ajax({
            method: 'post',
            data: {
                name: $("#name").val(),
                description: $("#description").val()
            },
            url: 'tags/create'
            }).done(function(response) {
                console.log(response)
                return true
            }).fail(function(response) {
                console.log(response)
        })
    }

    $("#submit").bind("click", function(event) {
        event.preventDefault()
        createTag()
    })

    updateTagTableModelandTable()

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
