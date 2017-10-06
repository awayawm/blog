<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>

    <h2>Welcome back.</h2>

    <form action="/login/authenticate" method="post">

    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" id="username" placeholder="Enter your username">
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" placeholder="Enter your password">
    </div>
    
    <button type="submit" class="btn btn-primary">Submit</button>

    </form>


</body>
</html>
