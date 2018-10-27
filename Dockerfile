FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/hello*.jar hello.jar
CMD java ${JAVA_OPTS} -jar hello.jar