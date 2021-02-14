FROM openjdk:latest
WORKDIR /opt/docker
ADD opt /opt
RUN ["chown", "-R", "daemon:daemon", "."]
USER daemon
RUN ["chmod", "755", "/opt/docker/bin/skakka"]
EXPOSE 9500
ENTRYPOINT ["/opt/docker/bin/skakka"]