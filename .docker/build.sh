#!/bin/bash

set -e

MAVEN_CACHE_DIR=${MAVEN_CACHE_DIR:-$(pwd)/.m2}

if [[ -z "$GITHUB_ACTIONS" ]]; then
  echo "Running in local environment"
else
  MAVEN_CACHE_DIR=/tmp/.m2
  echo "Running in GitHub Actions environment"
fi

if [[ "$1" == '--debug' ]]; then
  ARGS='--progress=plain --no-cache'
else
  ARGS=''
fi

echo "Building Docker image..."

# shellcheck disable=SC2086
docker build ${ARGS} --build-arg MAVEN_CACHE_DIR="${MAVEN_CACHE_DIR}" -t oso:snapshot .
