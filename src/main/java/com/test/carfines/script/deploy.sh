#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/sweater-1.0-SNAPSHOT.jar \
    YourUsr@192.168.0.107:/home/YourUsr/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa YourUsr@192.168.0.107 << EOF
pgrep java | xargs kill -9
nohup java -jar TestWorkForCB-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'