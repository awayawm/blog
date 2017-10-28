<html>
    <head>
        <meta name="layout" content="main"/>
		<asset:javascript src="post.index.js"/>
    </head>
    <body>

        <g:applyLayout name="navbar">
        </g:applyLayout>

        <h2>Posts</h2>

        <table class="table table-dark">
            <thead>
            <tr>
              <th>Title</th>
              <th>Summary</th>
              <th>Link</th>
              <th>Enabled</th>
              <th>Content</th>
              <th>Actions</th>
            </tr>
            </thead>
            <tbody id="tagTable">
            </tbody>
        </table>

        <form name="postForm" id="postForm">

        <input type="hidden" id="id">

        <div class="form-group">
        <label for="name">Title</label>
        <input type="text" class="form-control" id="name" required placeholder="Post Title">
        </div>

        <div class="form-group">
        <label for="name">Link</label>
        <input type="text" class="form-control" id="link" required placeholder="shortcut-to-the-webpage">
        </div>

        <div class="form-group">
        <label for="content">Summary</label>
        <textarea class="form-control" id="summary" rows="3" placeholder="Post summary"></textarea>
        </div>

        <div class="form-group">
        <label for="content">Content</label>
        <textarea class="form-control" id="content" rows="12" placeholder="Never be game over!"></textarea>
        </div>

        <div class="form-group">
        <label class="form-check-label">
            <input id="enabled" class="form-check-input" type="checkbox">
            Enabled
        </label>
        </div>

        <input id="submit" type="button" class="btn btn-primary btn-block" value="Submit">

        </form>

    </body>
</html>
