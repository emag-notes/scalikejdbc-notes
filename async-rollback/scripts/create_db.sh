#!/usr/bin/env bash

ROOT_PASSWORD=${1:-root}
HOST=${2:-127.0.0.1}

mysql -uroot -p${ROOT_PASSWORD} -h ${HOST} < _create_db.sql
