<html>
    <head>
        <meta name="layout" content="main"/>
		<asset:javascript src="account.tag.js"/>
    </head>
    <body>

        <g:applyLayout name="navbar">
        </g:applyLayout>

        <h2>Tags</h2>

        <table class="table table-dark">
            <thead>
            <tr>
            <th>id</th>
            <th>name</th>
            <th>description</th>
            <th>actions</th>
            </tr>
            </thead>
            <tbody id="tagTable">
            </tbody>
        </table>

        <form name="tagForm" id="tagForm">

        <input type="hidden" id="id">

        <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" required placeholder="Tag name">
        </div>

        <div class="form-group">
        <label for="name">Description</label>
        <input type="text" class="form-control" id="description" required placeholder="Tag description">
        </div>

        <input id="submit" type="button" class="btn btn-primary btn-block" value="Submit">

        </form>

    </body>
</html>
