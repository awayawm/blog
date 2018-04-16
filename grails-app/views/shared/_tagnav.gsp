<nav class="nav justify-content-center my-4">
    <g:each in="${tags}" var="tag">
<a class="nav-link" href="/tags/${tag.shortUrl}"><h3>${tag.name}</h3></a>
    </g:each>
</nav>