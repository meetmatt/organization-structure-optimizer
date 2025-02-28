ARG MAVEN_VERSION=3.9.9
ARG JDK_VERSION=21
ARG DIST=alpine

ARG _MAVEN_TAG=${MAVEN_VERSION}-eclipse-temurin-${JDK_VERSION}-${DIST}

FROM maven:${_MAVEN_TAG} AS builder
WORKDIR /app
COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 mvn -ntp dependency:go-offline
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn -ntp validate test verify package site
RUN chmod a+r -R ./target

FROM eclipse-temurin:${JDK_VERSION}-jre AS runtime
WORKDIR /app
COPY --from=builder /app/.docker/entrypoint.sh ./bin/oso
COPY --from=builder /app/target/*.jar ./target/
ENTRYPOINT ["/app/bin/oso"]
