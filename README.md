## A Blog in Grails

The internet needs more blogs!

![alt text](https://s3-us-west-2.amazonaws.com/atronandbeyond.com/Animated+GIF-downsized.gif)

This application uses a system property called *BLOG_CONFIG* which is an absolute path to the config file on the file system.  An example of the config can be found in src/main/webapp/blog.config and if one can't be found from the system property then the one from the project will be loaded.

I recommend deploying this app on elastic beanstalk.  you'll need to set a few environmental properties

* blog_config - absolute path to config
* jdbc_connection_string - proper jdbc connection string or location of database
* password - username of database
* username - password of database

now you're set!

### Installation instructions

1. git clone project somewhere
2. change directories to the project directory.
3. Using intellij, open build.gradle located at the root of the project directory
4. run assemble gradle task to create a war file i.e. ./gradlew assemble
5. get war from build/libs and upload it to an elastic beanstalk environment

If you're feeling fancy, adjust the *deploy* gradle task to your project and deploy from the command line.  If might need to put your aws creds into ~/.aws/credentials inside a *blog* profile

### Todo

* ~~confirmation on tag/post deletion~~
* ~~sort posts by most recently modified~~
* ~~randomly show's X tags (from config)~~
* nice message on foreign key constraint error (attempting to delete tag that has a post associated with it)