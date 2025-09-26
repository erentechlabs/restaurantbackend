# =======================
# Build Stage
# =======================
FROM maven:3.9.6-eclipse-temurin-24 AS build
WORKDIR /app

# Maven cache için sadece pom.xml kopyala
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Şimdi tüm kaynak kodu kopyala ve build et
COPY src ./src
RUN mvn clean package -DskipTests

# =======================
# Runtime Stage
# =======================
FROM eclipse-temurin:24-jdk
WORKDIR /app

# Build stage’den jar’ı al
COPY --from=build /app/target/*.jar app.jar

# Portu expose et
EXPOSE 8080

# Container start komutu
ENTRYPOINT ["java", "-jar", "app.jar"]
