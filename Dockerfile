
FROM eclipse-temurin:25-jdk
WORKDIR /app

COPY . .

RUN chmod +x ./mvnw

ENV MAVEN_OPTS="-Xmx192m -XX:MaxMetaspaceSize=128m -XX:+UseSerialGC"

RUN ./mvnw clean package -DskipTests


RUN rm -f target/*-plain.jar target/*.original
RUN cp target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]