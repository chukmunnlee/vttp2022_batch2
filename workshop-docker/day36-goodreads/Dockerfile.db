FROM mysql:8.0

# Initialize the database
COPY goodreads.sql /docker-entrypoint-initdb.d

ENV MYSQL_ROOT_PASSWORD=rootroot
