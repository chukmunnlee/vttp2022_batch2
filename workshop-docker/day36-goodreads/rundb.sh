
docker run -d --name grdb \
	--network mynet \
	-v data-vol:/var/lib/mysql \
	chukmunnlee/goodreads-db:v1
