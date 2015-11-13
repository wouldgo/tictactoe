#!/bin/bash

DEFAULT_STAND='STANDALONE' && \
read -p "Specify if you want to go in standalone mode or in service mode [$DEFAULT_STAND]: " STANDALONE && \
STANDALONE=${STANDALONE:-$DEFAULT_STAND} && \

docker run \
-it \
-h tictactoe \
-e "STANDALONE=$(echo $STANDALONE)" \
-p 127.0.0.1:8081:8081 \
wouldgo/tictactoe
