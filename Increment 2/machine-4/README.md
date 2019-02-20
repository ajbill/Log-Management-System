# Machine 4

Set ethernet port

```
sudo ifconfig ens33 192.168.211.44

ip route add 192.168.211.10/24 dev ens33
```

Remove any existing containers and volumes

```
cd fabric-samples/Log-Management-System-Iteration-4/machine-4

docker kill $(docker ps -q)

docker rm $(docker ps -aq)

docker rmi $(docker images dev-* -q)

COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-machine4.yaml down --volumes --remove-orphan
```

Load container and attach peer to the channel

```
COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-machine4.yaml up -d

docker exec -e "CORE_PEER_MSPCONFIGPATH=/etc/hyperledger/msp/users/Admin@org1.log-network/msp" peer3.org1.log-network peer channel fetch config -o orderer.log-network:7050 -c oslogchannel

docker exec -e "CORE_PEER_MSPCONFIGPATH=/etc/hyperledger/msp/users/Admin@org1.log-network/msp" peer3.org1.log-network peer channel join -b oslogchannel_config.block
```
Return to [Increment 2 Iteration 2](../README.md) folder.
