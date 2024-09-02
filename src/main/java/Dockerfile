# Usando uma imagem do JDK 17 como base
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR gerado pelo Maven ou Gradle para o diretório de trabalho
COPY target/AppRH-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta que a aplicação usa
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
