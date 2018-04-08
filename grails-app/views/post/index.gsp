<html>
    <head>
        <meta name="layout" content="layout"/>
    </head>
    <body>

<div class="container-fluid">
<div class="row">
<div class="col">

        <g:render template="/shared/adminnav"/>

        <h1 class="display-4 my-3">Posts</h1>

        <g:if test="my-3 w-50 ${flash.title}">
            <div class="${flash.class}">
                <h4>${flash.title}</h4>
                <p>${flash.message}</p>
            </div>
        </g:if>


        <table class="table my-3">
            <thead>
            <tr>
            <th>title</th>
            <th>content</th>
            <th>summary</th>
            <th>shortUrl</th>
            <th>enabled</th>
            <th>assoc. tags</th>
            <th>image</th>
            <th>actions</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${posts}" var="post">
                 <tr>
                 <td>
                 ${post?.title}
                 </td>
                 <td>
                 ${post?.content}
                 </td>
                  <td>
                  ${post?.summary}
                  </td>
                  <td>
                  ${post?.shortUrl}
                  </td>
                <td>
                ${post?.enabled}
                </td>
                    <td>
                    ${post?.tags}
                    </td>
                 <td>
                 <img style="max-width: 200px;" src="data:${imageContentType};base64,${imageBytes?.encodeBase64()}"/>
                 </td>
                     <td>
                         <span onclick="window.location.href='/admin/posts?id=${post.id}'" class="mr-4"><i class="fas fa-edit"></i></span>
                         <span onclick="window.location.href='/admin/posts/deleteTag?id=${post.id}'" class="mr-4"><i class="fas fa-trash"></i></span>
                     </td>
                 </tr>
             </g:each>
            </tbody>
        </table>

        <form name="postForm" id="postForm" method="post" action="/admin/post/addEdit" class="border border-rounded p-4" enctype="multipart/form-data">

        <input type="hidden" name="id" value=${id}>

        <div class="form-group my-4">
            <input type="button" onclick="" value="Reset form" id="resetForm" class="btn btn-lg btn-primary"/>
        </div>

        <div class="form-group my-4">
            <label for="title"><h5>Title</h5></label>
            <input type="text" class="form-control form-control-lg" name="title" value="${title}" required placeholder="Post Title" required>
        </div>

        <div class="form-group my-4">
            <label for="link"><h5>shortUrl</h5></label>
            <input type="text" class="form-control form-control-lg" name="shortUrl" value="${shortUrl}" required placeholder="shortcut-to-the-webpage" required>
        </div>

        <div class="form-group  my-4">
            <label for="summary"><h5>Summary</h5></label>
            <textarea class="form-control form-control-lg" id="summary" rows="3" placeholder="Post summary" required>${summary}</textarea>
        </div>

        <div class="form-group  my-4">
            <label for="content"><h5>Content</h5></label>
            <textarea class="form-control form-control-lg" id="content" rows="8" placeholder="Content of the post" required>${content}</textarea>
        </div>

        <div class="form-check my-4">
            <input name="enabled" id="enabled" class="form-check-input" type="checkbox">
            <label for="enabled" class="form-check-label"><h5>Enabled</h5></label>
        </div>

        <div class="form-group my-4">
            <label for="tags"><h5>Tags</h5></label>

            <select multiple class="form-control" required>
                <g:each in="${tags}" var="tag">
                    <option>${tag.name}</option>
                </g:each>
            </select>
        </div>

        <input id="submit" type="button" class="my-4 btn btn-primary btn-lg" value="Submit">

        </form>

        <div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete Post?</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
               Do you want to delete <span id="postName"></span>?
              </div>
              <div class="modal-footer">
                <button id="confirmDeleteButton" type="button" class="btn btn-primary" data-dismiss="modal">Delete</button>
              </div>
            </div>
          </div>
        </div>

</div>
</div>
</div>

    </body>
</html>
