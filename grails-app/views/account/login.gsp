<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
	<asset:javascript src="account.login.js"/>
</head>
<body>

    <h2>Welcome back.</h2>

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

    <input id="submitButton" type="submit" class="btn btn-lg btn-primary btn-block" value="Login">

    </form>

</body>
</html>
