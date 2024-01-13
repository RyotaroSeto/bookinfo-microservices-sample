#!/usr/bin/env bash

cd $(dirname "$0")

source ./init.sh

${CATALINA_HOME}/bin/startup.sh
