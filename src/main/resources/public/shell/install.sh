#!/bin/bash
set -e
filename="$1"
filesuffix="${filename##*.}"
fileprefix="${filename%.*}"
fileprefix="${fileprefix%.*}"
registry_path="http://192.168.31.147:8080/"
install_tag="ddd"
port=0

if [ ! -n "$2" ];then
    port=$2
fi
# 检查端口

if [ 1 -eq 1 ]; then
	echo "port is ok!"
fi	
# 检测目录
if [ ! -d "${install_tag}" ]; then
	mkdir ${install_tag}
fi

PWD="${PWD}/${install_tag}"
cd ${PWD}

wget -q "${registry_path}/package/${filename}"

# 解压操作
if [ "${filesuffix}" = "gz" ]; then
    echo "tar gz-------";
	#tar -zxvf ${filename}
fi
if [ "${filesuffix}" = "tar" ]; then
	tar -xvf ${filename}
fi
if [ "${filesuffix}" = "zip" ]; then
	echo "zip---------";	
fi
if [ "${filesuffix}" = "sh" ]; then
    echo "PATH=$PWD:"'$PATH' >> ~/.bashrc
    `chmod 775 ${filename}`
fi

# 执行安装包内部脚本
install_file="${install_tag}/${fileprefix}/install.sh";
if [ -f "${install_file}" ]; then
    sh ${install_file}
fi
start_file="${install_tag}/${fileprefix}/start.sh"
if [ -f "${start_file}" ]; then
    sh ${start_file}
fi
