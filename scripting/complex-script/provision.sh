#!/bin/bash

# A simple example script that publishes a number of scripts to the Nexus Repository Manager
# and executes them.

# fail if anything errors
set -e
# fail if a function call is missing an argument
set -u

username=admin
password=admin123

# add the context if you are not using the root context
host=http://localhost:8081

function addAndRunScript {
  name=$1
  file=$2
  groovy -Dgroovy.grape.report.downloads=true addUpdatescript.groovy -u "$username" -p "$password" -n "$name" -f "$file" -h "$host"
  printf "\nPublished $file as $name\n"
  curl -v -X POST -u $username:$password --header "Content-Type: text/plain" "$host/service/siesta/rest/v1/script/$name/run"
  printf "\nSuccessfully executed $name script\n"
}

printf "Provisioning Integration API Scripts Starting \n\n" 
printf "Publishing and executing on $host\n"

addAndRunScript docker dockerRepositories.groovy
addAndRunScript npmBower npmAndBowerRepositories.groovy
addAndRunScript raw rawRepositories.groovy
addAndRunScript security security.groovy
addAndRunScript core core.groovy

printf "Provisioning Scripts Completed" 