#!/bin/bash

./mvnw clean package -DskipTests

scp target/harjoitusprojekti-0.0.1-SNAPSHOT.jar lottav@softala.haaga-helia.fi:

ssh lottav@softala.haaga-helia.fi '
pkill -9 -f "harjoitusprojekti-0.0.1-SNAPSHOT.jar" || true
./run-moviehub.sh
'