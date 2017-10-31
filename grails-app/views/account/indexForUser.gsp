<html>
    <head>
        <meta name="layout" content="main"/>
		<asset:javascript src="account.indexForUser.js"/>
    </head>
    <body>

    <h2>${session.account.username}'s Profile</h2>

    <p>
    <input id="logout" type="button" value="Logout" class="btn btn-primary" />
    </p>

    <form id="updateAccountForm">

    <input type="hidden" id="id">

    <div class="form-group">
    <label for="username">Username</label>
    <input disabled type="text" class="form-control" id="username" required>
    </div>

    <div class="form-group">
    <label for="fullname">Full name</label>
    <input type="text" class="form-control" id="fullname" required>
    </div>

    <div class="form-group">
    <label for="emailaddress">Email Address</label>
    <input type="text" class="form-control" id="emailaddress" required>
    </div>

    <input id="resetPasswordButton" type="button" class="btn btn-primary" value="Reset Password">

    <p>
    <div id="resetPasswordFields">
    <p>

    <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" id="password" required>
    </div>

    <div class="form-group">
    <label for="passwordconfirmation">Confirm Password</label>
    <input type="password" class="form-control" id="passwordconfirmation" required>
    </div>

    </p>
    </div>
    </p>

    <input id="submitButton" type="submit" class="btn btn-primary btn-block" value="Update Account">

    </form>

    </body>
</html>
