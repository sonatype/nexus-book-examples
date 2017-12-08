#!/bin/bash

anonymous=$1

printf "Setting Anonymous access to $anonymous\n\n"

curl -v -X POST -u admin:admin123 --header "Content-Type: text/plain" "http://localhost:8081/service/rest/v1/script/anonymous/run" -d $anonymous
