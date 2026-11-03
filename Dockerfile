# Estágio 1: Build da aplicação com Maven
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia os descritores de dependências para aproveitar cache de camadas
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte e compila gerando o JAR sem rodar testes unitários
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Imagem final leve para execução (JRE 17)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Cria usuário não-root para boas práticas de segurança
RUN useradd -m springuser
USER springuser

# Copia apenas o .jar gerado no primeiro estágio
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]