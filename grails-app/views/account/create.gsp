<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
	<asset:javascript src="account.create.js"/>
</head>
<body>

    <h2>Create user.</h2>

    <form id="createForm" action="/admin/account/create" method="post">

    <g:if test="${flash.success == 'false'}">
    fail lol
    </g:if>

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

    <input type="submit" id="submitButton" class="btn btn-lg btn-primary btn-block" value="Submit">

    </form>


</body>
</html>
