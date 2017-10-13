## atronandbeyond.com

#The internet needs more ranting people!

![alt text](https://s3-us-west-2.amazonaws.com/atronandbeyond/im-doing-my-part.png)

This blog uses the Grails web framework

It's currently configured to use Apache Derby but of course you can change this to whatever you want if you have the right jdbc connector and a running datebase daemon.

Once you've sorted out your DB, clone the project and run gradle to bring in the dependencies. Finally run grails run-app to launch the blog.  Go to /account/create to create an admin account.

Set a SECRET_KEY environmental variable to salt the hashing functions ex. export SECRET_KEY=blogymcblogface

http://db.apache.org/derby/
https://grails.org/

