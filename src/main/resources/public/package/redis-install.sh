#!/bin/bash
set -e
#### local val ##############################
url="http://localhost:8080/package/"
wget="/usr/local/bin/wget"
redis_file="redis-3.2.6.tar.gz"
port=8008
config_file="cluster/"${port}

#### start install ###########################
echo ">> start wget redis package ..."
${wget} ${url}${redis_file} | xargs echo

echo ">> start mkdir dir ${config_file} ..."
mkdir -p ${config_file}

echo ">> start tar -zxvf ${redis_file}"
tar -zxvf ${redis_file}

echo ">> cd redis"

echo ">> install finish!!!"