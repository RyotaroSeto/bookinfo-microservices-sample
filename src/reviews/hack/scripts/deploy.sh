#!/usr/bin/env bash

cd $(dirname "$0")

source ./init.sh

ps -f | grep "org.apache.catalina.startup.Bootstrap" | grep -v "grep" > /dev/null
if [ $? -eq 0 ]; then
    ./shutdown.sh
fi

APP_CONTEXT=reviews

rm ${CATALINA_BASE}/webapps/${APP_CONTEXT}.war
rm -rf ${CATALINA_BASE}/webapps/${APP_CONTEXT}

pushd ../../
    ./mvnw clean package
    cp ./target/${APP_CONTEXT}.war ${CATALINA_BASE}/webapps/
popd

./startup.sh
