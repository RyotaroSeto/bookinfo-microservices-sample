#!/usr/bin/env bash

cd $(dirname "$0")

source ./init.sh

${CATALINA_HOME}/bin/shutdown.sh

echo "Tomcat stopped."