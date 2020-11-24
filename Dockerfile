FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/stakeLimitService.jar appx.jar
ENTRYPOINT ["java","-jar","appx.jar"]