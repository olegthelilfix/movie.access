#!/usr/bin/env bash

if [ $1 = "build" ]; then
    killall java
    nohup mvn spring-boot:run &
fi

if [ $1 = "stop" ]; then
    killall java
fi