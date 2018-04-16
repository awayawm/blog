<!doctype html>
<html>
<title></title>
<head>
  <meta name="layout" content="layout"/>
</head>

  <body>

<div class="container-fluid">
<div class="row">
<div class="col">

<div class="text-center my-3">
<h1 class="display-4">${title}</h1>
<h1 class="mb-5"><small class="text-muted">${tagline}</small></h1>
</div>

<g:render template="/shared/tagnav"/>

<g:render template="/shared/indexalert"/>

<div class="card mb-3">
  <img class="card-img-top img-fluid" src="data:${post.imageContentType};base64,${post.imageBytes.encodeBase64()}" alt="Card image cap">
  <div class="card-body">
    <h2 class="card-title">${post.title}</h2>
    <p class="card-text">${post.content}</p>

    <input type="button" class="btn btn-lg my-4" onclick="window.history.back()" value="back"/>
    </div>
  <div class="card-footer text-right">
  <span class="text-muted"><g:formatDate date="${post.lastUpdated}" type="datetime" style="MEDIUM"/></span>
  </div>

</div>

<g:render template="/shared/footer"/>

</div>
</div>
</div>

  </body>

</html>
