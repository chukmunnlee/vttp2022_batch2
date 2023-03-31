
docker network create mynet

docker volume create data-vol 

docker run -d --name grdb \
	--network mynet \
	-v data-vol:/var/lib/mysql \
	chukmunnlee/goodreads-db:v1

docker run -d -p 8080:8080 --name grapp \
	--network mynet \
	-e SPRING_DATASOURCE_URL=jdbc:mysql://grdb:3306/goodreads \
	chukmunnlee/goodreads:v1
