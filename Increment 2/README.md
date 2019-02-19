# Increment 2 - Iteration 2

Install prereqs on each machine




cd Log-Management-System-Iteration-2/Increment 2

docker kill $(docker ps -q)

docker rm $(docker ps -aq)

docker rmi $(docker images dev-* -q)

COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-cli.yaml down --volumes --remove-orphan

composer card delete --card PeerAdmin@log-network

composer card delete --card admin@log-network

COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-cli.yaml -f docker-compose-couch.yaml -f docker-compose-cas.yaml up -d

docker exec cli scripts/script.sh oslogchannel 3 golang 10 false

cd log-network

composer card create -p connection.json -u PeerAdmin -c Admin@org1.log-network-cert.pem -k *_sk -r PeerAdmin -r ChannelAdmin -f PeerAdmin@log-network.card

composer card import -f PeerAdmin@log-network.card --card PeerAdmin@log-network

composer network install --card PeerAdmin@log-network --archiveFile log-network@0.0.1.bna

composer network start --networkName log-network --networkVersion 0.0.1 -A admin -S adminpw -c PeerAdmin@log-network

composer card import -f admin@log-network.card


