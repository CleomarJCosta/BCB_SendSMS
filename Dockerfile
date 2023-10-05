# Use a imagem oficial do OpenJDK para Java 17
FROM openjdk:17-jdk-alpine
# Copie o arquivo JAR da aplicação para o contêiner
COPY target/sendSMS-1.0.jar sendsms-app.jar

ENTRYPOINT ["java", "-jar", "sendsms-app.jar"]
