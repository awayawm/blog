<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
	<asset:javascript src="account.create.js"/>
</head>
<body>

    <h2>Create user.</h2>

    <form id="form" action="/account/create" method="post">

    <div class="form-group">
        <label for="username">Username</label>
        <input required type="text" name="username" class="form-control" id="username" placeholder="Enter your username">
        <span id="usernameRequired">Username is required</span>
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input required type="password" name="password" class="form-control" id="password" placeholder="Enter your password">
        <span id="passwordRequired">Password is required</span>
    </div>

    <button type="submit" id="submit" class="btn btn-lg btn-primary btn-block">Submit</button>

    </form>


</body>
</html>
