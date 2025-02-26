ARG MAVEN_VERSION=3.9.9
ARG JDK_VERSION=21
ARG DIST=alpine

ARG _MAVEN_TAG=${MAVEN_VERSION}-eclipse-temurin-${JDK_VERSION}-${DIST}

FROM maven:${_MAVEN_TAG} AS builder
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:${JDK_VERSION}-jre AS runtime
WORKDIR /app
COPY .docker/entrypoint.sh ./bin/oso
COPY --from=builder /app/target/*.jar ./target/
ENTRYPOINT ["/app/bin/oso"]
