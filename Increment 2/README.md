# Install the prerequisites

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

echo "# Installing Docker-Compose" sudo curl -L "https://github.com/docker/compose/releases/download/1.13.0/docker-compose-$(uname -s)-$(uname -m)" 

-o /usr/local/bin/docker-compose sudo chmod +x /usr/local/bin/docker-compose

sudo apt-get -y install unzip

# Log out and back in again

# Install Composer and development tools

npm install -g composer-cli@0.20.6

npm install -g composer-rest-server@0.20.6

npm install -g generator-hyperledger-composer@0.20.6

npm install -g yo

npm install -g composer-playground@0.20.6

# Install Hyperledger Fabric

curl -sSL http://bit.ly/2ysbOFE | bash -s 1.2.1 1.2.1 0.4.10

cd fabric-samples

# Download this repository

