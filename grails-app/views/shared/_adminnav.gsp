<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/">blog</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">

      <li class="nav-item ${params.controller == 'post' ? "active" : ""}">
        <a class="nav-link" href="/admin/posts">Posts</a>
      </li>

        <li class="nav-item ${params.controller == 'tag' ? "active" : ""}">
          <a class="nav-link" href="/admin/tags">Tags</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="https://analytics.google.com/analytics">Analytics</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="/logout">Logout</a>
        </li>
    </ul>
  </div>
</nav>

