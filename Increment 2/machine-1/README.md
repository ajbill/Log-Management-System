docker kill $(docker ps -q)

docker rm $(docker ps -aq)

docker rmi $(docker images dev-* -q)

COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-machine1.yaml down --volumes --remove-orphan


# Machine 1


set adapter to bridged (need NAT aswel for when installing chaincode)

sudo ifconfig ens33 192.168.211.11

COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-machine1.yaml up -d

docker exec cli scripts/script.sh oslogchannel 3 golang 10 false
