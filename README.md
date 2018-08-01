## A Blog in Grails

The internet needs more blogs!

![alt text](https://s3-us-west-2.amazonaws.com/atronandbeyond.com/Animated+GIF-downsized.gif)

This application uses a system property called *BLOG_CONFIG* which is an absolute path to the config file on the file system.  An example of the config can be found in src/main/webapp/blog.config and if one can't be found from the system property then the one from the project will be loaded.

you'll need to set a few environmental properties

* BLOG_CONFIG - absolute path to config
* JDBC_CONNECTION_STRING - proper jdbc connection string or location of database
* PASSWORD - username of database
* USERNAME - password of database

now you're set!

### Installation instructions

1. git clone project somewhere
2. change directories to the project directory.
3. Using intellij, open build.gradle located at the root of the project directory
4. run assemble gradle task to create a war file i.e. ./gradlew assemble
5. set up your environmental variables and run grails run-app

#### TODO
1. reset password