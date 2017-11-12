#!/bin/sh

./gradlew clean build

java -jar build/libs/titlefinder-1.0.jar
