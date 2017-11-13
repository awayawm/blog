$(document).ready(function() {
    var Posts = []

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
                                  "<svg id='" + post.id + "' height='32' class='deleteButton octicon octicon-x' viewBox='0 0 12 16' version='1.1' width='24' aria-hidden='true'><path fill-rule='evenodd' d='M7.48 8l3.75 3.75-1.48 1.48L6 9.48l-3.75 3.75-1.48-1.48L4.52 8 .77 4.25l1.48-1.48L6 6.52l3.75-3.75 1.48 1.48z'></path></svg>" +
                                  "</td>" +
                                  "</tr>")
        })

    }

    var getPosts = function() {
        $.ajax({
            url: '/admin/posts/getposts',
            method: 'get'
        }).done(function(res) {
            renderTable(res.data.posts)
        })
    }

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
            $("#postForm")[0].reset()
            getPosts()
        })
    })

    getPosts()

})

$(document).on('click', '.deleteButton', function(event) {
        console.log(event.target.id)
})