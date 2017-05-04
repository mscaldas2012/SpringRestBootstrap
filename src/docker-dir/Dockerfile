FROM openjdk
VOLUME /tmp

ADD spring.bootstrap.jar .

RUN sh -c 'touch /springRestBootstrap.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","spring.bootstrap.jar"]