# Clear any existing containers

docker kill $(docker ps -q)

docker rm $(docker ps -aq)

docker rmi $(docker images dev-* -q)

COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-machine2.yaml down --volumes --remove-orphan

# Machine 2

sudo ifconfig ens33 192.168.211.22

COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-machine2.yaml up -d

# Join Channel

docker exec -e "CORE_PEER_MSPCONFIGPATH=/etc/hyperledger/msp/users/Admin@org1.log-network/msp" peer1.org1.log-network peer channel fetch config -o orderer.log-network:7050 -c oslogchannel

docker exec -e "CORE_PEER_MSPCONFIGPATH=/etc/hyperledger/msp/users/Admin@org1.log-network/msp" peer1.org1.log-network peer channel join -b oslogchannel_config.block
