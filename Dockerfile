FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/*.jar

# cd /opt/app
WORKDIR /opt/app

ENV KANDONE_DATABASE_USERNAME=default
ENV KANDONE_DATABASE_PASSWORD=default

# cp target /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-Dspring.datasource.url=${KANDONE_DATABASE_URL}", "-Dspring.datasource.username=${KANDONE_DATABASE_USERNAME}", "-Dspring.datasource.password=${KANDONE_DATABASE_PASSWORD}", "-jar","app.jar"]

#Set your config after build application on maven. Command: mvn clear package
# docker build -t kandone-back .
# docker run -p 8080:8080 -e KANDONE_DATABASE_USERNAME='sa' -e KANDONE_DATABASE_PASSWORD='123' kandone-back