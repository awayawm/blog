var getPosts = function() {
    $.ajax({
        url: '/admin/posts/getposts',
        method: 'get'
    }).done(function(res) {
        console.log(res.data.posts)
        renderTable(res.data.posts)
    })
}

var renderTable = function(posts) {
    $("#postTable").empty()
    $("#postTable").append("<table class='table table-dark'>" +
                     "<thead>" +
                     "<tr>" +
                     "<th>Id</th>" +
                     "<th>Title</th>" +
                     "<th>Summary</th>" +
                     "<th>Link</th>" +
                     "<th>Enabled</th>" +
                     "<th>Content</th>" +
                     "<th>Actions</th>" +
                     "</tr>" +
                     "</thead>" +
                     "<tbody id='tagTable'>" +
                     "</tbody>" +
                     "</table>")

    posts.forEach(function(post) {
        $("#tagTable").append("<tr>" +
                              "<th>" + post.id + "</th>" +
                              "<td>" + post.title + "</td>" +
                              "<td>" + post.summary + "</td>" +
                              "<td>" + post.link + "</td>" +
                              "<td>" + post.enabled + "</td>" +
                              "<td>" + post.content + "</td>" +
                              "<td>" +
                              "<svg height='32' class='octicon octicon-x' viewBox='0 0 12 16' version='1.1' width='24' aria-hidden='true'><path id='" + post.id + "' class='deleteButton' fill-rule='evenodd' d='M7.48 8l3.75 3.75-1.48 1.48L6 9.48l-3.75 3.75-1.48-1.48L4.52 8 .77 4.25l1.48-1.48L6 6.52l3.75-3.75 1.48 1.48z'></path></svg>" +
                              "<svg height='32' class='octicon octicon-pencil' viewBox='0 0 14 16' version='1.1' width='28' aria-hidden='true'><path id='" + post.id + "' class='editButton' fill-rule='evenodd' d='M0 12v3h3l8-8-3-3-8 8zm3 2H1v-2h1v1h1v1zm10.3-9.3L12 6 9 3l1.3-1.3a.996.996 0 0 1 1.41 0l1.59 1.59c.39.39.39 1.02 0 1.41z'></path></svg>" +
                              "</td>" +
                              "</tr>")
    })
}

var getPosts = function() {
    $.ajax({
        url: '/admin/posts/getposts',
        method: 'get'
    }).done(function(res) {
//        console.log(res)
        if(res.success) {
            renderTable(res.data.posts)
        }
    })
}

$(document).ready(function() {

    $("#submit").bind("click", function(e) {

        var data = {
            title: $("#title").val(),
            link: $("#link").val(),
            summary: $("#summary").val(),
            content: $("#content").val(),
            enabled: $("#enabled").prop("checked"),
            tags: $("#tags").val()
        }

        var id = $("#id").val()
        if(id)
            data['id'] = id

        $.ajax({
            url: '/admin/posts/submit',
            data: data,
            method: 'post',
            dataType: 'json',
        }).done(function(res) {
            $("#postForm")[0].reset()
            getPosts()
        })
    })

    $("#confirmDeleteButton").bind("click", function(event) {
        var id = $("#postName").text()
        $.ajax({
            url: '/admin/posts/deletepost',
            method: 'post',
            data: {id: id}
        }).done(function(res) {
            getPosts()
        })
    })

    getPosts()

})

$(document).on('click', '.deleteButton', function(event) {

    $("#postName").text(event.target.id)
    $("#confirmDeleteModal").modal({
        show: true
    })

})

$(document).on('click', '.editButton', function(event) {
//        console.log(event.target)
    if(event.target.id) {

        $.ajax({
            url: '/admin/posts/getpost/' + event.target.id,
            method: 'get'
        }).done(function(res) {
            if(res.success) {
                console.log(res.data.post)
                $("#id").val(res.data.post.id)
                $("#title").val(res.data.post.title)
                $("#link").val(res.data.post.link)
                $("#content").val(res.data.post.content)
                $("#summary").val(res.data.post.summary)
                $("#enabled").val(res.data.post.enabled)
            }
        })
     }
})