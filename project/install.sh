#!/bin/bash

# Install Java
sudo apt-get update
sudo apt-get install -y openjdk-11-jdk

# Install Maven
sudo apt-get install -y maven

# Verify installation
java -version
mvn -version