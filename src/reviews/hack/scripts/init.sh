#!/usr/bin/env bash

# initialize sdkman
[[ -s "$SDKMAN_DIR/bin/sdkman-init.sh" ]] && source "$SDKMAN_DIR/bin/sdkman-init.sh"

# tomcat environment variables
export JAVA_HOME=$(sdk home java current)
export CATALINA_HOME=$(sdk home tomcat current)
export CATALINA_BASE=$(pwd)/../../.catalina-base
