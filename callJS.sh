#!/usr/bin/env bash
echo "Hello from bash"
echo "Try call java. . ."
RESULT=$(jjs src/main/js/callJava.js)
echo "Answer from java: $RESULT"