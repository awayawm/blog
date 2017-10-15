## atronandbeyond.com

The internet needs more ranting people!

![alt text](https://s3-us-west-2.amazonaws.com/atronandbeyond/im-doing-my-part.png)

This blog uses the Grails framework for web applications.

It's currently configured to use Apache Derby but of course you can change this to whatever you want if you have the right JDBC connector and the database daemon running

Set a SECRET_KEY environmental variable to salt the hashing functions.  In Debian you could run export SECRET_KEY=blogymcblogface in bash or make the change permanent in your .bashrc

Once you've sorted out your DB, clone the project and run gradle to bring in the dependencies. Now you're ready to run grails run-app to launch the blog.  Go to /account/create to create an admin account.  Once the first admin account is created, /account/create will only be accessable by users with the Admin role

http://db.apache.org/derby/
https://grails.org/
http://34.208.159.120:8080

