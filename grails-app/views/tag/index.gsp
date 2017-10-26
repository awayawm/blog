<html>
    <head>
        <meta name="layout" content="main"/>
		<asset:javascript src="account.tag.js"/>
    </head>
    <body>

        <g:applyLayout name="navbar">
        </g:applyLayout>

        <h2>Tags</h2>

        <form>

        <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" placeholder="Tag name">
        </div>

        <div class="form-group">
        <label for="name">Description</label>
        <input type="text" class="form-control" id="description" placeholder="Tag description">
        </div>

        <input type="submit" class="btn btn-primary btn-block" value="Submit">

        </form>

    </body>
</html>
