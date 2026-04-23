#!/bin/bash
set -e

SERVER=root@time.pikker.dev

./gradlew test jar --info
(cd ui && npm run test:run && npm run check && npm run build)

echo "Started building"
DOCKER_DEFAULT_PLATFORM=linux/amd64 docker compose build
echo "Finished building"

echo "Moving image to server"
docker save time-tracker-app | gzip | ssh $SERVER 'gunzip | docker load'

echo "Copying composes to server"
scp compose.yml compose.server.yml $SERVER:timetracker/
ssh $SERVER << END
  cd timetracker && \
  docker compose -f compose.yml -f compose.server.yml up -d --wait && \
  docker logs -f timetracker-app-1
END
