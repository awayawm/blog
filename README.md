## A Blog in Grails

The internet needs more ranting people!

![alt text](https://s3-us-west-2.amazonaws.com/atronandbeyond.com/Animated+GIF-downsized.gif)

uses a system property called *blog-config* that points to a configuration file on the filesystem.  an example of the config can be found in src/main/webapp/blog.config and if one can't be found from the system property then it will be loaded.

### Installation instructions

1. git clone project somewhere
2. open build.gradle inside project directory in intellij
3. run assemble gradle task to create a war file i.e. ./gradlew assemble
4. get war from build/lib and upload it to an elastic beanstalk environment

If you're feeling fancy, adjust the deploy gradle task to your project and deploy from the command line.

### Sprint

* date/time on post, tag admin display
* confirmation on tag/post deletion
* google analytics
* test that nothing breaks when 0 posts, 0 tags
* social media icons in footer
* don't show post when disabled and don't allow access
* get dynamic titles working
* get favicon working