FROM node:19

## Building the image
# create the /app directory
# cd into /app
WORKDIR /app
# in /app

# copy files
COPY main.js .
COPY package.json .
COPY package-lock.json .
# copy directories
COPY public public
COPY views views

# install packages
RUN npm i

## Running the container
ENV PORT=3000
ENV INSTANCE_NAME="my app"

HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 \
    CMD curl http://localhost:${PORT}/healthz || exit 1

# what port the application listens to
EXPOSE ${PORT}

ENTRYPOINT node main