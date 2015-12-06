@echo off
set /p dev.profile="Enter Dev Profile:"
@echo on
mvn clean test -Pdev-db-recreate -Ddev.profiles.active=%dev.profile%