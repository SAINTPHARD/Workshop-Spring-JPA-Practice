# 1. Usar uma imagem base do Java 17
FROM openjdk:17-slim

# 2. Definir um diretório de trabalho dentro do container
WORKDIR /app

# 3. Copiar o .jar que o Maven criou (o *.jar é um coringa para o nome)
COPY target/*.jar app.jar

# 4. Expor a porta 8080 (que o Spring usa)
EXPOSE 8080

# 5. Definir o comando para rodar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]