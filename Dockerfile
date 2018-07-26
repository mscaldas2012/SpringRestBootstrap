FROM openjdk
VOLUME /tmp

ADD target/spring.bootstrapTemplate-*.jar ./springRestBootstrap.jar

RUN sh -c 'touch /springRestBootstrap.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","springRestBootstrap.jar"]