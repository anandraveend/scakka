FROM openjdk:latest
# workspace folder where jenkins will be executing the sbt build
#ARG WORKSPACE   
#ENV workspace_dir=${WORKSPACE}
WORKDIR /opt/docker
#ADD ${workspace_dir}/target/docker/stage/opt /opt
ADD target/docker/stage/opt /opt
RUN ["chown", "-R", "daemon:daemon", "."]
USER daemon
RUN ["chmod", "755", "/opt/docker/bin/skakka"]
EXPOSE 9500
ENTRYPOINT ["/opt/docker/bin/skakka"]