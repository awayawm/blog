$(document).ready(function() {

    var tagTable = []

    function updateTagTable() {
        $("#tagTable").empty()
        for (item in tagTable) {
        console.log(tagTable[item])
        $("#tagTable").append(  "<tr>" +
                                "<td>" + tagTable[item].id + "</td>" +
                                "<td>" + tagTable[item].name + "</td>" +
                                "<td>" + tagTable[item].description + "</td>" +
                                "<td>" + "</td>" +
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
                updateTagTableModelandTable()
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

    updateTagTableModelandTable()

    $("#submit").bind("click", function(event) {
        event.preventDefault()
        createTag()
    })




})