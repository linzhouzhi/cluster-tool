#!/usr/bin/env bash
registry_path="http://localhost:8080/node/"
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
    ;;
    "pull" )
    	echo "pull"
    ;;
    "push" )
        if [ $# -le 2 ] ; then
            echo "please confirm param .. upload file image-name";
            exit 1;
        fi
        echo "start upload....."
        curl "${registry_path}upload_package" -F "package=@${filename}"
    ;;
    * )
        echo "$help_msg"
    ;;
esac
