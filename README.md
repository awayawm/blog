## A Blog in Grails

The internet needs more ranting people!

![alt text](https://s3-us-west-2.amazonaws.com/atronandbeyond/im-doing-my-part.png)

It's configured for mongodb but you can change this to your favorite dbms with some tweaking.

Once you've sorted out your DB, clone the project and run gradle to bring in the dependencies. 

Now you're ready to run grails run-app to launch the blog.  Go to /admin/account/create to create an admin account.  Once the first admin account is created, /admin/account/create will only be accessible by users with the Admin role

You'll also need to set some environmental variables

ANALYTICS_SECRET_KEY: Google analytics server key  
SECRET_KEY: Used by password hashing function  

http://atronandbeyond.com

