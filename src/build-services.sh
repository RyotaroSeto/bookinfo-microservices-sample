#!/bin/bash
#
# Copyright Istio Authors
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.

set -o errexit

if [ "$#" -ne 2 ]; then
    echo "Incorrect parameters"
    echo "Usage: build-services.sh <version> <prefix>"
    exit 1
fi

VERSION=$1
PREFIX=$2
SCRIPTDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

pushd "$SCRIPTDIR/productpage"
  docker build --pull -t "${PREFIX}/productpage:${VERSION}" -t "${PREFIX}/productpage:latest" .
popd

pushd "$SCRIPTDIR/details"
  docker build --pull -t "${PREFIX}/details:${VERSION}" -t "${PREFIX}/details:latest" --build-arg service_version=v1 .
popd

pushd "$SCRIPTDIR/reviews"
  docker build --pull -t "${PREFIX}/reviews:${VERSION}" -t "${PREFIX}/reviews:latest" --build-arg service_version=v1 .
popd

pushd "$SCRIPTDIR/mysql"
  docker build --pull -t "${PREFIX}/mysqldb:latest" .
popd
