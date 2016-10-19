FROM openjdk
VOLUME /tmp

ADD nlpPrimer.jar .

RUN sh -c 'touch /springRestBootstrap.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","springRestBootstrap.jar"]