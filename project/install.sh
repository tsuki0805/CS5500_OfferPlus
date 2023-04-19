#!/bin/bash

# Install Java
apt-get update
apt-get install -y openjdk-11-jdk

# Install Maven
apt-get install -y maven

# Verify installation
java -version
mvn -version