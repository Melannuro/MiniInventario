# --- Etapa 1: Construcción (Build) ---
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar el archivo de configuración de dependencias
COPY pom.xml .

# Descargar las dependencias (esto optimiza el tiempo de construcción en Render mediante caché)
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Compilar y empaquetar el proyecto ignorando las pruebas para agilizar el despliegue
RUN mvn clean package -DskipTests

# --- Etapa 2: Ejecución (Run) ---
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
LABEL authors="Artur Dommy"

# Copiar únicamente el archivo JAR generado desde la etapa de construcción
COPY --from=build /app/target/MiniInventario-0.0.1-SNAPSHOT.jar app_miniinventario.jar

# Exponer el puerto configurado
EXPOSE 8085

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app_miniinventario.jar"]