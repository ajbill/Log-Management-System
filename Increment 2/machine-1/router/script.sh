#!/bin/bash

sudo iptables -t nat -A POSTROUTING -o ens33 -j MASQUERADE

sudo iptables -A FORWARD -i ens33 -o ens34 -m state --state RELATED,ESTABLISHED -j ACCEPT

sudo iptables -A FORWARD -i ens34 -o ens33 -j ACCEPT
