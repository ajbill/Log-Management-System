#!/bin/sh

sudo apt update
sudo apt upgrade

# install Java
sudo apt install software-properties-common
sudo add-apt-repository ppa:webupd8team/java
sudo apt update
sudo apt install oracle-java8-set-default
echo "JAVA_HOME=/usr/lib/jvm/java-8-oracle/jre/bin/java" | sudo dd of=/etc/environment oflag=append conv=notrunc
sleep 3

# install gradle
sudo apt update
sudo apt install gradle
sleep 3

# Set LD_LIBRARY_PATH to include libjvm.so:
export LD_LIBRARY_PATH="/usr/lib/jvm/java-8-oracle/jre/lib/amd64/server"
# Make it permanent
echo "/usr/lib/jvm/java-8-oracle/jre/lib/amd64/server" | sudo dd of=/etc/ld.so.conf.d/your_lib.conf oflag=append conv=notrunc
# Update the cache
sudo ldconfig
sleep 3

# install syslog-ng packages
wget -qO - http://download.opensuse.org/repositories/home:/laszlo_budai:/syslog-ng/xUbuntu_16.04/Release.key | sudo apt-key add -
sudo dd of=/etc/apt/sources.list.d/syslog-ng-obs.list << EOF
deb http://download.opensuse.org/repositories/home:/laszlo_budai:/syslog-ng/xUbuntu_16.04 ./
EOF
sudo apt update
sleep 3
sudo apt install syslog-ng-core
sleep 3
sudo apt install syslog-ng-mod-kafka

# Edit syslog-ng config file to include new Kafka destination
sudo dd of=/etc/syslog-ng/conf.d/kafka.conf << EOF
@module mod-java
# kafka destination
destination d_kafka {
  kafka(
    client-lib-dir("/usr/lib/syslog-ng/3.18/java-modules/kafka.jar:/opt/kafka_2.12-2.0.1/libs")
    kafka-bootstrap-servers("127.0.0.1:9092")
    topic("blockchainLogs")
  );
};

log { source(s_src); destination(d_kafka); };
EOF

# Install Kafka
wget http://apache.mirror.anlx.net/kafka/2.0.1/kafka_2.12-2.0.1.tgz
tar xzf kafka_2.12-2.0.1.tgz
sudo mv kafka_2.12-2.0.1 /opt/
cd /opt/kafka_2.12-2.0.1/

# Start Zookeeper
x-terminal-emulator -e /opt/kafka_2.12-2.0.1/bin/zookeeper-server-start.sh config/zookeeper.properties

sleep 5

# Start Kafka server
x-terminal-emulator -e /opt/kafka_2.12-2.0.1/bin/kafka-server-start.sh config/server.properties

sleep 5

# Create Log Rotate topic/channel
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic rotateLogs

# Create Blochchain topic/channel
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic blockchainLogs

# Run a Kafka consumer
x-terminal-emulator -e bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic blockchainLogs --from-beginning

# Restart syslog-ng
systemctl restart syslog-ng

