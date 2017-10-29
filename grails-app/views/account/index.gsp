<html>
    <head>
        <meta name="layout" content="main"/>
		<asset:javascript src="account.index.js"/>
    </head>
    <body>

        <g:applyLayout name="navbar">
        </g:applyLayout>

        <h2>Accounts</h2>

        <table class="table">
            <thead class="thead-inverse">
                <tr>
                    <th>Id</th>
                    <th>Username</th>
                    <th>Full Name</th>
                    <th>Email Address</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${accounts}" var="account">
                    <tr>
                        <th>${account.id}</th>
                        <td>${account.username}</td>
                        <td>${account.fullName}</td>
                        <td>${account.emailAddress}</td>
                        <td>${account.role}</td>
                        </td>
                        <td>
                            <span accountId="${account.id}" class="editAccountIcon octicon octicon-pencil"></span> 
							<span accountId="${account.id}" class="deleteAccountIcon octicon octicon-x"></span> 
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>

	<p>
		<input id="createAccountButton" type="button" class="btn btn-primary" value="Create Account">
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

    <div class="form-group">
      <label for="role">Role</label>
      <select id="role" class="form-control">
        <g:each in="${roles}" var="role">
          <option>${role}</option>
        </g:each>
      </select>
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
