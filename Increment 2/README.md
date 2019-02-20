# Increment 2 Iteration 1

Install the prerequisites

```
sudo apt install curl -y

sudo snap install node --classic --channel=8

# restart terminal

curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.2/install.sh | bash

node -v nvm

# restart terminal

nvm install v8.9.0

nvm use 8.9.0

sudo apt-add-repository -y ppa:git-core/ppa

sudo apt-get update

sudo apt-get install -y git

sudo apt-get -y install apt-transport-https ca-certificates

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

sudo apt-get update

apt-cache policy docker-ce

sudo apt-get install -y docker-ce

sudo usermod -aG docker $(whoami)

sudo curl -L "https://github.com/docker/compose/releases/download/1.13.0/docker-compose-$(uname -s)-$(uname -m)" \
    -o /usr/local/bin/docker-compose
    
sudo chmod +x /usr/local/bin/docker-compose

sudo apt-get -y install unzip

# Log out and back in again
```

Install Composer and development tools

```
npm install -g composer-cli@0.20.6

npm install -g composer-rest-server@0.20.6

npm install -g generator-hyperledger-composer@0.20.6

npm install -g yo

npm install -g composer-playground@0.20.6
```

Install Hyperledger Fabric

```
curl -sSL http://bit.ly/2ysbOFE | bash -s 1.2.1 1.2.1 0.4.10

cd fabric-samples

# Download this repository

unzip Log-Management-System-Iteration-1

cd LMS-Increment-2-Iteration-1

```

Start the network

```
COMPOSE_PROJECT_NAME=byfn docker-compose -f docker-compose-cli.yaml -f docker-compose-couch.yaml -f docker-compose-cas.yaml up -d

chmod +x scripts/script.sh

chmod +x scripts/utils.sh

docker exec cli scripts/script.sh oslogchannel 3 golang 10 false
```

# Create a business card and connect Composer to the network

```
cd log-network

composer card create -p connection.json -u PeerAdmin -c Admin@org1.log-network-cert.pem -k *_sk -r PeerAdmin -r ChannelAdmin -f PeerAdmin@log-network.card

composer card import -f PeerAdmin@log-network.card --card PeerAdmin@log-network

composer network install --card PeerAdmin@log-network --archiveFile log-network@0.0.1.bna

// composer network install --card PeerAdmin@log-network --archiveFile log-network@0.0.1.bna -o npmrcFile=/home/andrew/.npm/npmConfig.txt

composer network start --networkName log-network --networkVersion 0.0.1 -A admin -S adminpw -c PeerAdmin@log-network

composer card import -f admin@log-network.card
```

Test the network connection

```
composer network ping -c admin@log-network
```

Generate a REST server

```
composer-rest-server
```

- Enter the name of the business network card to use: admin@log-network
- Specify if you want namespaces in the generated REST API: Never
- Specify if you want to use an API key to secure the REST API: No
- Specify if you want to enable authentication for the REST API using Passport: No
- Specify if you want to enable the explorer test interface: Yes
- Specify a key if you want to enable dynamic logging: 
- Specify if you want to enable event publication over WebSockets: No
- Specify if you want to enable TLS security for the REST API: No

