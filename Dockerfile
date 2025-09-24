# ====== Build stage ======
FROM eclipse-temurin:24-jdk-jammy AS build
WORKDIR /app

# Leverage Docker layer caching
COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw mvnw
RUN chmod +x mvnw
RUN ./mvnw -q -B -e -DskipTests dependency:go-offline

# Copy sources and build
COPY src src
RUN ./mvnw -q -B -DskipTests package

# ====== Runtime stage ======
FROM eclipse-temurin:24-jre-jammy AS runtime
WORKDIR /app

# Create non-root user
RUN useradd -r -s /bin/false appuser
USER appuser

# Copy fat jar from build (adjust the pattern if your final name differs)
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

# Expose typical Spring Boot port; adjust if different
EXPOSE 8080

# Health and graceful shutdown
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -XX:InitialRAMPercentage=50 \
               -XX:+ExitOnOutOfMemoryError"
ENV SPRING_PROFILES_ACTIVE=prod

# Optional: timezone and file.encoding (uncomment if needed)
# ENV TZ=UTC
# ENV LANG=C.UTF-8 LANGUAGE=C.UTF-8 LC_ALL=C.UTF-8

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]