<!doctype html>
<html>
<head>
    <meta name="layout" content="layout"/>
	<asset:javascript src="account.login.js"/>
	<script async defer src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>

    <h2>Welcome back.</h2>

    <div id="unsuccesfulLogin" class="collapse alert alert-danger alert-dismissible">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
          <h4 class='alert-heading'>Opps!</h4>
          <p class="mb-0">Please verify your username and password and try again.</p>
    </div>

    <form id="loginForm" method="post">

    <div class="form-group">
        <label for="username">Username</label>
        <input required type="text" class="form-control" id="username" placeholder="Enter your username">
        <span id="usernameRequired">Username is required</span>
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input required type="password" class="form-control" id="password" placeholder="Enter your password">
        <span id="passwordRequired">Password is required</span>
    </div>
    
    <div class="form-group">
    <label class="form-check-label">
        <input id="remember_me" class="form-check-input" type="checkbox">
        Remember me
    </label>
    </div>

    <g:insertGoogleCaptcha/>

    </form>

</body>
</html>
