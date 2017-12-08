#!/bin/bash

name=$1

printf "Deleting Integration API Script $name\n\n"

curl -v -X DELETE -u admin:admin123  "http://localhost:8081/service/rest/v1/script/$name"
