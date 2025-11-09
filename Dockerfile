# ------------------------------------------------------------------
# 1. ESTÁGIO DE BUILD (Compilação e Empacotamento do Quarkus)
# ------------------------------------------------------------------
# Usa a imagem base do Maven e Java
FROM maven:3.9.6-openjdk-21 AS build

# Define o diretório de trabalho
WORKDIR /workspace/app

# Copia o código fonte (pom.xml e pasta src)
COPY pom.xml .
COPY src src

# Empacota a aplicação Quarkus em um JAR executável
# O '-DskipTests' é usado para ignorar testes durante o build
RUN mvn package -DskipTests

# ------------------------------------------------------------------
# 2. ESTÁGIO FINAL (Runtime)
# ------------------------------------------------------------------
# Usa uma imagem JRE leve para a produção
FROM openjdk:21-jre-slim

# Define o diretório onde a aplicação irá rodar
WORKDIR /app

# Copia os arquivos do estágio de build (o JAR final)
# O target/quarkus-app é a estrutura otimizada do Quarkus
COPY --from=build /workspace/app/target/quarkus-app/ /app

# Define a porta que a aplicação Quarkus irá expor
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "/app/quarkus-run.jar"]