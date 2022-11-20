FROM openjdk:17
ADD target/cakeecommerce.jar cakeecommerce.jar
ENTRYPOINT ["java", "-jar", "cakeecommerce.jar"]