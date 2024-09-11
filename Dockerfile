# Use uma imagem base com JDK apropriado
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o JAR construído para o diretório de trabalho
COPY target/bookstore-0.0.1-SNAPSHOT.jar /app/bookstore.jar

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "bookstore.jar"]