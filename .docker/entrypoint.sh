#!/bin/sh

cp ./target/oso-*.jar ./target/oso.jar

# shellcheck disable=SC2086
exec java ${JAVA_OPTS} -jar "./target/oso.jar" "$@"
