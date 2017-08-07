tar -zxvf redis-3.2.9.tar.gz
   63  ls
   64  cd redis-3.2.9
   65  ls
   66  make
   67  make
   68  apt-get install make
   69  unmae
   70  uname
   71  uname -a
   72  apt-get install gcc gcc-c++
   73  apt-get install gcc
   74  apt-get update
   75  apt-get install gcc
   76  ls
   77  make
   78  apt-get install make
   79  make
   80  make install
   
start.sh------------   
#!/bin/bash

sed -i "s/{port}/$1/g" redis.conf
./src/redis-server redis.conf (不要有这个 &

sudo docker run redis-common /redis/redis-3.2.9/start.sh 8008
sudo docker run -it redis-common /redis/redis-3.2.9/src/redis-cli -h 127.0.0.1 -p 8008
   
   