#!/bin/bash

name=$1
jsonFile=$2

printf "Updating Integration API Script $name with $jsonFile\n\n"

curl -v -X PUT -u admin:admin123 --header "Content-Type: application/json" "http://localhost:8081/service/rest/v1/script/$name" -d @$jsonFile
