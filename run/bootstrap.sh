#!/bin/bash

if [ $STANDALONE = "STANDALONE" ]; then

  java -jar /tictactoe/standalone/target/standalone-0.0.1-SNAPSHOT.jar
else

  cd /tictactoe/web-api && /apache-maven-3.3.3/bin/mvn jetty:run
fi
