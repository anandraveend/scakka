# scakka - steps to dockerise a scala akka-http application

######## STEPS TO INSTALL SBT IF REQUIRED ###############

---

## Java
`sudo apt-get update`

`sudo apt-get install default-jdk`

---

## Scala

`sudo apt-get remove scala-library scala`

`sudo wget http://scala-lang.org/files/archive/scala-2.12.1.deb`

`sudo dpkg -i scala-2.12.1.deb`

`sudo apt-get update`

`sudo apt-get install scala`

---

## SBT
`echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list`

`sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823`

`sudo apt-get update`

`sudo apt-get install sbt`

---

### DOCKER STEPS
##### Add docker plugin
`echo 'addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.6")' >> projects/plugins.sbt`


##### Enable plugin in build .sbt
`echo 'enablePlugins(JavaAppPackaging)' > build.sbt.new`

`cat build.sbt >> build.sbt.new`

`mv build.sbt.new build.sbt`

##### Run sbt stage to create a runnable script for the app
`sbt stage`

##### Run sbt docker:stage to create a Dockerfile for application
`sbt docker:stage`

##### Run sbt docker:publishLocal if you want to publish image to local docker repository
`sbt docker:publishLocal`


##### To run as a docker container
`docker run -p 9500:9500 -d <app>:<version>`


# Openshift commands
##### To add a user to cluster admin role

##### To add OC registry to docker insecure registry list

`sudo vi /etc/docker/daemon.json`

##### To add  user as cluster admin

`oc adm policy add-cluster-role-to-user cluster-admin anand`

##### To logic to OC docker registry

###### Get OC registry info

`oc registry info`

###### Get the current oc user password 

`oc whoami -t`

###### Create docker image tag against the repo

`docker login -u anand -p HLyOMekQ8E9G7vDrov_ccOJ3jX9XBkP0xT5aAGyQsv4 172.30.1.1:5000`

`docker tag skakka:latest 172.30.1.1:5000/myproject/skakka`

`docker push 172.30.1.1:5000/myproject/skakka`

