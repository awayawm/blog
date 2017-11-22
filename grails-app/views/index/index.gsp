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

<h1><g:getConfigTitle></g:getConfigTitle></h1>
<p class="lead"><g:getConfigTagline></g:getConfigTagline></p>

<g:each in="${posts}" var="post">
<p>
<h3><a href="/${post.link}">${post.title}</a></h3>
<p>
${post.summary}
</p>
<p>
${post.datemodified}
<svg class="octicon octicon-star" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M14 6l-4.9-.64L7 1 4.9 5.36 0 6l3.6 3.26L2.67 14 7 11.67 11.33 14l-.93-4.74z"></path></svg>
<a href="/${post.link}">Read Post</a>
</p>
</p>
</g:each>
</body>
</html>
