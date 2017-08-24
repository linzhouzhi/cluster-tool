#!/usr/bin/env bash

case "$1" in
    "list" )
        echo "list"
    ;;
    "upload" )
        if [ $# -le 2 ] ; then
            echo "please confirm param .. upload file image-name";
            exit 1;
        fi
        echo "start upload....."
        curl http://localhost:8080/node/upload_package -F "package=@$2"
    ;;
    * )
        echo "only support list and upload"
    ;;
esac
