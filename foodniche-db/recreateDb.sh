#!/bin/bash

read -e -p "Enter Dev Profile:" devProfile
echo "Using $devProfile profile"
mvn clean test -Pdev-db-recreate -Ddev.profiles.active=$devProfile