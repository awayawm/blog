## A Blog in Grails

The internet needs more ranting people!

![alt text](https://s3-us-west-2.amazonaws.com/atronandbeyond/im-doing-my-part.png)

run ./gradlew bootRun from the source directory to launch the application.  Go to /admin/account/create to create an admin account.  Once the first admin account is created, /admin/account/create will only be accessible by users with the Admin role

in application.yml the production data source probably needs to be changed

the line<br>
_jndiName: java:comp/env/productionDatasource_<br>
could be replaced with<br>
_jdbc:h2:mem:prodDB;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE_<br>
if don't have a need to mess with JNDI settings

You also can set some environmental variables, but if you don't set a SECRET_KEY the code will choose a horrible one for you so the can application boot.

ANALYTICS_SECRET_KEY: Google analytics server key  
SECRET_KEY: Used by password hashing function