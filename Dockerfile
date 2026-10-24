#Imagen base de Java 
FROM eclipse-temurin:25-jdk

#Directorio de trabajo dentro del contenedor
WORKDIR /app

# Archivo JAR generado por Maven al contenedor
COPY target/order-managment-0.0.1-SNAPSHOT.jar app.jar

#Puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.mongodb.uri=mongodb://admin:secret@liverpool-mongo:27017/liverpool_db?authSource=admin"]