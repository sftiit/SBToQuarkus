#!/bin/bash

docker exec -i mysql-user mysql -u root --password=NeG18$ test < db_tables/lineitems.sql
