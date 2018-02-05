#!/bin/bash

printf "Listing Integration API Scripts\n"

curl -v -u admin:admin123 'http://localhost:8081/service/rest/v1/script'
