#!/bin/bash

mongoimport --host=localhost --port=27017 \
	--db=netflix --collection=tvshows \
	--jsonArray /opt/tmp/tv-shows.json
