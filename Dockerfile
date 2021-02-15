FROM openjdk:latest
# workspace folder where jenkins will be executing the sbt build
WORKDIR /opt/docker
ADD target/docker/stage/opt /opt
RUN ["chown", "-R", "daemon:daemon", "."]
USER daemon
RUN ["chmod", "755", "/opt/docker/bin/scala-http"]
EXPOSE 9500
ENTRYPOINT ["/opt/docker/bin/scala-http"]