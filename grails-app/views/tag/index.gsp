<html>
    <head>
        <meta name="layout" content="layout"/>
    </head>
    <body>

<div class="container-fluid">
<div class="row">
<div class="col">

        <g:render template="/shared/adminnav"/>

        <h1 class="display-4 my-3">Tags</h1>

        <table class="table my-3">
            <thead>
            <tr>
            <th>id</th>
            <th>name</th>
            <th>description</th>
            <th>image</th>
            <th>assoc. posts</th>
            <th>actions</th>
            </tr>
            </thead>
            <tbody>



            </tbody>
        </table>

        <form name="tag" method="post" action="/admin/tags" class="border p-4" enctype="multipart/form-data">

        <input type="hidden" id="id">

        <div class="form-group">
            <label for="name"><h5>Name</h5></label>
            <input type="text" class="form-control form-control-lg" name="name" id="name" required placeholder="Tag name">
        </div>

        <div class="form-group">
            <label for="description"><h5>Description</h5></label>
            <textarea rows="4" class="form-control form-control-lg" id="description" name="description" required placeholder="Tag description"></textarea>
        </div>

          <div class="form-group">
            <label for="tagImage">Tag Image</label>
            <input type="file" class="form-control-file" id="tagImage" name="image" required>
          </div>

            <div class="text-center my-4">
                <input type="submit" class="btn btn-primary btn-lg" value="Submit"/>
            </div>
        </form>

</div>
</div>
</div>

    </body>
</html>
