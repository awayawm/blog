<!doctype html>
<html>
<title>
</title>
<head>
<meta name="layout" content="layout"/>
<g:getConfigFavicon></g:getConfigFavicon>
<g:insertGoogleAnalytics></g:insertGoogleAnalytics>
</head>
<body>

<div class="row">
<div class="col-sm-8">

<h1><g:getConfigTitle></g:getConfigTitle></h1>
<h2><g:getConfigTagline></g:getConfigTagline></h2>

<g:each in="${posts}" var="post">
<g:if test="${post.enabled == true}">
<p>
<h3><a href="/${post.link}">${post.title}</a></h3>
<p>
${post.summary}
</p>
<p>
<span class="date">
<g:formatDate date="${post.datemodified}" type="datetime" style="LONG"/>
</span>
<svg class="octicon octicon-star" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M14 6l-4.9-.64L7 1 4.9 5.36 0 6l3.6 3.26L2.67 14 7 11.67 11.33 14l-.93-4.74z"></path></svg>
<a class="read-post" href="/${post.link}">Read Post</a>
</p>
</p>
</g:if>
</g:each>

</div>
</div>

</body>
</html>
