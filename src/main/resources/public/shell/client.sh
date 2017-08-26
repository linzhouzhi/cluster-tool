#!/bin/bash
registry_path="http://localhost:8080/"
help_msg="only support list,pull and push"
type=$1
filename=$2

if [ $# -le 1 ]; then
    echo "$help_msg";
    exit 1;
fi
case "${type}" in
    "list" )
        echo "list"
        curl "${registry_path}node/list_package"
    ;;
    "pull" )
    	echo "pull"
    	curl -O "${registry_path}package/${filename}"
    ;;
    "push" )
        if [ $# -le 2 ] ; then
            echo "please confirm param .. upload file image-name";
            exit 1;
        fi
        echo "start upload....."
        curl "${registry_path}node/push_package" -F "package=@${filename}"
    ;;
    * )
        echo "$help_msg"
    ;;
esac
