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

<g:render template="/shared/header"/>

<g:render template="/shared/tagnav"/>

<g:render template="/shared/indexalert"/>

<div class="card mb-3 shadow-lg">
  <img class="card-img-top img-fluid" src="data:${post.imageContentType};base64,${post.imageBytes.encodeBase64()}" alt="Card image cap">
  <div class="card-body">
    <h4 class="card-title my-3">${raw(post.title)}</h4>
    <hr>
    <p class="card-text my-4">${raw(post.content)}</p>

<div>
<g:each in="${post.tags}" var="tag">
<a href="/tags/${tag.shortUrl}" class="badge badge-secondary">${tag.name}</a>
</g:each>
</div>

    <input type="button" class="btn btn-lg my-4 bg-gradient-secondary text-white" onclick="window.history.back()" value="back"/>
    </div>

  <div class="card-footer">
  <span class="text-muted"><g:formatDate date="${post.lastUpdated}" type="datetime" style="MEDIUM"/></span>
  </div>

</div>

<g:render template="/shared/footer"/>

</div>
</div>
</div>

  </body>

</html>
