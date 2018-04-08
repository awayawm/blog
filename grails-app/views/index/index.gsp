<!doctype html>
<html>
<title>
</title>
<head>
  <meta name="layout" content="layout"/>
</head>

  <body>

<div class="container-fluid">
<div class="row">
<div class="col">

<h1 class="display-4">${title}</h1>
<h1><small class="text-muted">${tagline}</small></h1>

<nav class="nav my-4">
    <g:each in="${tags}" var="tag">
<a class="nav-link" href="/tags/${tag.shortUrl}">${tag.name}</a>
    </g:each>
</nav>

<g:each status="counter" in="${posts}"  var="post">

    <g:if test="${counter%2 == 0}">
        <div class="card-group">
    </g:if>

  <div class="card">
    <img class="card-img-top img-fluid" src="data:${post.imageContentType};base64,${post.imageBytes.encodeBase64()}" alt="Card image cap">
    <div class="card-body">
      <h5 class="card-title">${post.title}</h5>
      <p class="card-text">${post.summary}</p>
      <p class="card-text"><small class="text-muted">${post.lastUpdated}o</small></p>
    </div>
  </div>

    <g:if test="${counter%2 != 0}">
        </div>
    </g:if>

    <g:if test="${(counter == posts.size()-1) && (counter%2 == 0)}">
    </div>
    </g:if>

</g:each>



</div>
</div>
</div>

  </body>

</html>
