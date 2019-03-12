# Machine 1

## Part 1

Install Ubuntu 16.04.5-desktop-amd64 on a new virtual machine with the following hardware settings:

- Number of processors: 1
- Number of cores per processor: 1
- Memory 256 MB
- Disk: 20 GB

- Adapters etc

Configure the router 

```
edit /etc/sysctl.conf and uncomment:

# net.ipv4.ip forward=1

# restart router

sudo ifconfig ens33 192.168.211.10

cd ~/??????????

sudo iptables -t nat -A POSTROUTING -o ens33 -j MASQUERADE

sudo iptables -A FORWARD -i ens33 -o ens34 -m state --state RELATED,ESTABLISHED -j ACCEPT

sudo iptables -A FORWARD -i ens34 -o ens33 -j ACCEPT



sudo iptables -t nat -A POSTROUTING -o ens33 -j MASQUERADE

sudo iptables -A FORWARD -i ens33 -o ens38 -m state --state RELATED,ESTABLISHED -j ACCEPT

sudo iptables -A FORWARD -i ens38 -o ens33 -j ACCEPT
```

## Part 2

On the other virtual machine:

Set internal ethernet port

```
sudo ifconfig ens33 192.168.211.11 default gateway?
```

Remove any existing containers and volumes

```
cd ~/Log-Management-System-Iteration-2/Increment\ 2/machine-1

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
Return to [Increment 2 Iteration 2](../README.md) folder.

## Part 3

Delete any existing cards

```
composer card delete --card PeerAdmin@log-network

composer card delete --card admin@log-network
```

Create the Composer connection and install the chaincodes

```
cd log-network

composer card create -p connection.json -u PeerAdmin -c Admin@org1.log-network-cert.pem -k *_sk -r PeerAdmin -r ChannelAdmin -f PeerAdmin@log-network.card

composer card import -f PeerAdmin@log-network.card --card PeerAdmin@log-network

composer network install --card PeerAdmin@log-network --archiveFile log-network@0.0.1.bna

composer network start --networkName log-network --networkVersion 0.0.1 -A admin -S adminpw -c PeerAdmin@log-network

composer card import -f admin@log-network.card
```

