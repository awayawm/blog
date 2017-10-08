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
        <input type="text" name="username" class="form-control" id="username" placeholder="Enter your username">
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" name="password" class="form-control" id="password" placeholder="Enter your password">
    </div>
    
    <div class="form-group">
    <label class="form-check-label">
        <input class="form-check-input" name="remember_me" type="checkbox">
        Remember me
    </label>
    </div>

    <button type="submit" class="btn btn-lg btn-primary btn-block">Submit</button>

    </form>


</body>
</html>
