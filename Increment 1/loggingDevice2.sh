#!/bin/sh

sudo apt update 

sudo apt install apache2 -y

sudo ufw app list

# open port 80

sudo ufw allow 'Apache'

sudo ufw status

# sudo systemctl status apache2

# install apache benchmark

sudo apt install apache2-utils

sudo cp /etc/syslog-ng/conf.d/kafka.conf /etc/syslog-ng/conf.d/kafka.old

sudo dd of=/etc/syslog-ng/conf.d/kafka.conf << EOF
source s_apache2 { file("/var/log/apache2/access.log" flags(no-parse)); };

@module mod-java
# kafka destination
destination d_kafka {
  kafka(
    client-lib-dir("/usr/lib/syslog-ng/3.18/java-modules/kafka.jar:/opt/kafka_2.12-2.0.1/libs")
    kafka-bootstrap-servers("127.0.0.1:9092")
    topic("blockchainLogs")
  );
};

log { source(s_apache2); destination(d_kafka); };
EOF

# Start Zookeeper
x-terminal-emulator -e /opt/kafka_2.12-2.0.1/bin/zookeeper-server-start.sh config/zookeeper.properties

sleep 5

# Start Kafka server
x-terminal-emulator -e /opt/kafka_2.12-2.0.1/bin/kafka-server-start.sh config/server.properties

sleep 5

systemctl restart syslog-ng



