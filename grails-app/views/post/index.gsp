<html>
    <head>
        <meta name="layout" content="layout"/>
		<asset:javascript src="post.index.js"/>
    </head>
    <body>

        <g:applyLayout name="navbar-admin"></g:applyLayout>
        <p>
        <h2>Posts</h2>
        </p>
        <div id="postTable">
        </div>

        <form id="postForm">

        <input type="hidden" id="id">

        <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title" required placeholder="Post Title" required>
        </div>

        <div class="form-group">
        <label for="link">Link</label>
        <input type="text" class="form-control" id="link" required placeholder="shortcut-to-the-webpage" required>
        </div>

        <div class="form-group">
        <label for="summary">Summary</label>
        <textarea class="form-control" id="summary" rows="3" placeholder="Post summary" required></textarea>
        </div>

        <div class="form-group">
        <label for="content">Content</label>
        <textarea class="form-control" id="content" rows="12" placeholder="Never be game over!" required></textarea>
        </div>

        <div class="form-group">
        <label class="form-check-label">
            <input id="enabled" class="form-check-input" type="checkbox">
            Enabled
        </label>
        </div>

        <div class="form-group">
        <label for="tags">Tags</label>
        <select multiple required class="form-control" id="tags">
        </select>
        </div>

        <input id="submit" type="button" class="btn btn-primary btn-block" value="Submit">

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

    </body>
</html>
