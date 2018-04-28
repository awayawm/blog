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
  <img class="card-img-top img-fluid" src="data:${tag.imageContentType};base64,${tag.imageBytes.encodeBase64()}" alt="Card image cap">
  <div class="card-body">
    <h1 class="display-4 card-title">${tag.name.encodeAsRaw()}</h1>
    <p class="card-text lead">${tag.description.encodeAsRaw()}</p>
    <input type="button" class="btn btn-lg my-4 bg-gradient-secondary text-white" onclick="window.history.back()" value="back"/>
  </div>
</div>

<g:each status="counter" in="${posts}"  var="post">

    <g:if test="${counter%2 == 0}">
        <div class="card-group">
    </g:if>

  <div class="card shadow-lg">
    <img class="card-img-top img-fluid" src="data:${post.imageContentType};base64,${post.imageBytes.encodeBase64()}" alt="Card image cap">
    <div class="card-body">
      <h4 class="card-title my-3"><a href="/posts/${post.shortUrl}">${post.title}</a></h4>
      <p class="card-text my-4">${post.summary.encodeAsRaw()}</p>
      </div>
          <div class="m-3">
          <g:each in="${post.tags}" var="tag">
          <a href="/tags/${tag.shortUrl}" class="badge badge-secondary">${tag.name}</a>
          </g:each>
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

<g:render template="/shared/footer"/>

</div>
</div>
</div>

  </body>

</html>
