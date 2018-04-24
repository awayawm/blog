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

<g:each status="counter" in="${posts}"  var="post">

    <g:if test="${counter%2 == 0}">
        <div class="card-group">
    </g:if>

  <div class="card shadow-lg">
    <img class="card-img-top img-fluid" src="data:${post.imageContentType};base64,${post.imageBytes.encodeBase64()}" alt="Card image cap">
    <div class="card-body">
      <h5 class="card-title"><a href="/posts/${post.shortUrl}">${raw(post.title)}</a></h5>
      <p class="card-text">${raw(post.summary)}</p>

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
