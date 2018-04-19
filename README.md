## A Blog in Grails

The internet needs more ranting people!

![alt text](https://s3-us-west-2.amazonaws.com/atronandbeyond.com/Animated+GIF-downsized.gif)

uses a system property called *blog-config* that points to a configuration file on the filesystem.  an example of the config can be found in src/main/webapp/blog.config and if one can't be found from the system property then it will be loaded.

### Installation instructions

1. git clone project somewhere
2. change directories to the project directory.
3. Using intellij, open build.gradle located at the root of the project directory
4. run assemble gradle task to create a war file i.e. ./gradlew assemble
5. get war from build/libs and upload it to an elastic beanstalk environment

If you're feeling fancy, adjust the deploy gradle task to your project and deploy from the command line.

### Sprint

* ~~date/time on post, tag admin display~~
* confirmation on tag/post deletion
* google analytics
* animation on index view
* ~~fix timezone~~
* sort index by most recently modified
* new column on tag, enabled, that shows tag in nav bar or not
* compile bootstrap for drop shadows and gradients
* tags on bottom of post
* randomly show's X tags (from config).  this will cause articles on main page to be randomized