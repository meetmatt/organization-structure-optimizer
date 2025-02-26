#!/bin/sh

docker run -v "$PWD/src/test/resources/test.csv":/tmp/test.csv:ro oso:snapshot /tmp/test.csv