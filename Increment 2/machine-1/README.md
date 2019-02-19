# Machine 1

## Part 1

Install Ubuntu 16.04.5-desktop-amd64 on a new virtual machine with the following hardware settings:

- Ram
- Disk
- Adapters etc

Configure the router 

```
sudo ifconfig ens33 192.168.211.10

cd ~/??????????

sudo iptables -t nat -A POSTROUTING -o ens33 -j MASQUERADE

sudo iptables -A FORWARD -i ens33 -o ens34 -m state --state RELATED,ESTABLISHED -j ACCEPT

sudo iptables -A FORWARD -i ens34 -o ens33 -j ACCEPT
```

## Part 2

Set internal ethernet port

```
sudo ifconfig ens33 192.168.211.11 default gateway?
```

Remove any existing containers and volumes

```
cd fabric-samples/Log-Management-System-Iteration-2/machine-1

docker kill $(docker ps -q)

docker rm $(docker ps -aq)

docker rmi $(docker images dev-* -q)

COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-machine1.yaml down --volumes --remove-orphan
```

Load containers, set channel and attach peer

```
COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-machine1.yaml up -d

docker exec cli scripts/script.sh oslogchannel 3 golang 10 false
```


