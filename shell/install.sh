#!/bin/bash
filename="$1"
filesuffix="${filename##*.}"
fileprefix="${filename%.*}"
fileprefix="${fileprefix%.*}"
install_tag="ddd"
port=$2

# 检查端口
if [ 0 -eq `netstat -nat | grep ${port} | wc -l` ]; then
	echo "port is ok!"
fi	
# 检测目录
if [ ! -d "${install_tag}" ]; then
	mkdir ${install_tag}
fi

# 解压操作
if [ "${filesuffix}" = "gz" ]; then
	tar -zxvf ${filename} -C ${install_tag}
	echo "tar gz-------";
fi
if [ "${filesuffix}" = "tar" ]; then
	tar -xvf ${filename} -C ${install_tag}
fi
if [ "${filesuffix}" = "zip" ]; then
	echo "zip---------";	
fi

# 执行安装包内部脚本
sh ${install_tag}/${fileprefix}/install.sh
sh ${install_tag}/${fileprefix}/start.sh
