#!/bin/sh

#first install jq by using below command
#sudo apt install -y jq
curl ipinfo.io
echo "**********************************************************************"
echo "                                CREATING CONTAINER                    "
echo "**********************************************************************"
DOCKER_VERSION=$(curl -L -s 'https://registry.hub.docker.com/v2/repositories/jbirtharia/todo-web-application-mysql/tags/' | jq . | grep name | grep '[0-9].[0-9]'|tail -1|tr -d '\"' | tr -d 'name: ' | tr -d ',')
echo "DOCKER VERSION : "$DOCKER_VERSION
docker container stop app
docker container rm app
docker run -d --name app -p 80:8080 jbirtharia/todo-web-application-mysql:$DOCKER_VERSION
docker logs -f app &> app.log &