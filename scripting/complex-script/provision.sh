#!/bin/bash

# fail if anything errors
set -e
# fail if a function call is missing an argument
set -u

username=admin
password=admin123
host=http://localhost:8081

function addAndRunScript {
  name=$1
  file=$2
  groovy -Dgroovy.grape.report.downloads=true addUpdatescript.groovy -u "$username" -p "$password" -n "$name" -f "$file" -h "$host"
  echo "Published $file as $name"
  curl -v -X POST -u admin:admin123 --header "Content-Type: text/plain" "$host/service/siesta/rest/v1/script/$name/run"
  echo "Successfully executed $name script"
}

echo "Provisioning Scripts Starting" 
echo "Publishing and executing on $host"

addAndRunScript docker dockerRepositories.groovy
addAndRunScript npmBower npmAndBowerRepositories.groovy
addAndRunScript raw rawRepositories.groovy
