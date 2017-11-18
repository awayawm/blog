<!doctype html>
<html>
<head>
    <meta name="layout" content="layout"/>
	<asset:javascript src="account.create.js"/>
</head>
<body>

<g:if test="${session.account.role == 'Admin'}">
<g:applyLayout name="navbar-admin"></g:applyLayout>
</g:if>


    <p>
    <h2>Create user.</h2>
    </p>

    <form id="createForm" action="/admin/account/create" method="post">

    <div class="form-group">
        <label for="username">Username</label>
        <input required type="text" name="username" class="form-control" id="username" placeholder="Enter your username" />
        <span id="usernameRequired">Username is required</span>
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input required type="password" name="password" class="form-control" id="password" placeholder="Enter your password" />
        <span id="passwordRequired">Password is required</span>
    </div>

    <input type="submit" id="submitButton" class="btn btn-lg btn-primary btn-block" value="Submit">

    </form>

    <p class="mt-3">
    <input type="button" class="btn btn-primary" id="backtoaccount" value="Back to Account"/>
    </p>

</body>
</html>
