# Docker

docker restart $(docker ps -aq)

docker stop $(docker ps -aq)

docker start $(docker ps -aq)

docker restart/stop/start $(containerId)

docker kill $(docker ps -q)

docker rm $(docker ps -aq)

COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-cli.yaml down --volumes --remove-orphan

# Restart existing REST server

composer-rest-server -c admin@log-network -n never -u true -d y -w true

# Delete existing business cards

composer card delete --card PeerAdmin@log-network

composer card delete --card admin@log-network
