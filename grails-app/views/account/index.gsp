<html>
    <head>
        <meta name="layout" content="main"/>
    </head>
    <body>

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
                        <td></td>
                        <td></td>
                    </tr>
                </g:each>
            </tbody>
        </table>

    </body>
</html>
