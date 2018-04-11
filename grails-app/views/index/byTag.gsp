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

<input type="button" class="btn btn-lg my-4" onclick="window.history.back()" value="back"/>

<div class="card mb-3">
  <img class="card-img-top img-fluid" src="data:${tag.imageContentType};base64,${tag.imageBytes.encodeBase64()}" alt="Card image cap">
  <div class="card-body">
    <h1 class="display-4 card-title">${tag.name}</h1>
    <p class="card-text lead">${tag.description}</p>
  </div>
</div>


<g:each status="counter" in="${posts}"  var="post">

    <g:if test="${counter%2 == 0}">
        <div class="card-group">
    </g:if>

  <div class="card">
    <img class="card-img-top img-fluid" src="data:${post.imageContentType};base64,${post.imageBytes.encodeBase64()}" alt="Card image cap">
    <div class="card-body">
      <h5 class="card-title"><a href="/posts/${post.shortUrl}">${post.title}</a></h5>
      <p class="card-text">${post.summary}</p>
      </div>
    <div class="card-footer">
    <small class="text-muted"><g:formatDate date="${post.lastUpdated}" type="datetime" style="MEDIUM"/></small>
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
