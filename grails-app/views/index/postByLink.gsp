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

<h1>${post.title}</h2>

<hr>

<p class="single-post">
${post.content}
</p>

<p class="big-date">
Last modified on
<g:formatDate date="${post.datemodified}" type="datetime" style="SHORT"/>
by ${post.lastmodifiedby.username}
</p>

<input type="button" value="Back" onClick="window.location.href='/'" class="btn btn-default"/>

</body>
</html>
