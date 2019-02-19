# Machine 1

## Part 1

Set internal ethernet port

```
sudo ifconfig ens33 192.168.211.11
```

Remove any existing containers and volumes

```
docker kill $(docker ps -q)

docker rm $(docker ps -aq)

docker rmi $(docker images dev-* -q)

COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-machine1.yaml down --volumes --remove-orphan
```

Load containers, set channel and attach peer

set internal ethernet port

```
COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-machine1.yaml up -d

docker exec cli scripts/script.sh oslogchannel 3 golang 10 false
```
