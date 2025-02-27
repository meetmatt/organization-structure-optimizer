#!/bin/bash

set -e

if [[ -z "$GITHUB_ACTIONS" ]]; then
  echo "Running in local environment"
else
  echo "Running in GitHub Actions environment"
fi

if [[ "$1" == '--debug' ]]; then
  ARGS='--progress=plain --no-cache'
else
  ARGS=''
fi

echo "Building Docker image..."

# shellcheck disable=SC2086
DOCKER_BUILDKIT=1 docker build ${ARGS} -t oso:builder --target=builder .

# shellcheck disable=SC2086
DOCKER_BUILDKIT=1 docker build ${ARGS} -t oso:snapshot --target=runtime .
