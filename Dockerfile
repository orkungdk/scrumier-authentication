# The first stage - Builds the "deployable package"
FROM maven:3.6.1-jdk-8-alpine as backend-build
WORKDIR /services/authentication

## Step 1 - Copy pom.xml file and download the project dependencies. -B refers the batch mode
COPY pom.xml .
RUN mvn dependency:go-offline -B

## Step 2 - Copy source and build "deployable package".
COPY src src
RUN mvn install
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# The second stage - Builds a minimal image with the "deployable package"
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/services/authentication/target/dependency
COPY --from=backend-build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=backend-build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=backend-build ${DEPENDENCY}/BOOT-INF/classes /app
# Exposes the microservice on the following port number...
EXPOSE 8091

ENTRYPOINT ["java","-cp","app:app/lib/*", "tr.com.ogedik.authentication.AuthenticationApplication"]
##############################################################
# You can contact me via email or create an issue on github. #
# Email: orkungedik90@gmail.com                              #
# Github: https://github.com/orkungdk/authentication/issues  #
##############################################################
MAINTAINER orkun.gedik