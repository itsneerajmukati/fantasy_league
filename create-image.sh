docker build -t jre:1.0 -f Dockerfile .
cd user-service
docker build -t user-service:1.0 -f Dockerfile.user .
cd ../team-service
docker build -t team-service:1.0 -f Dockerfile.team .
cd ../player-service
docker build -t player-service:1.0 -f Dockerfile.player .
cd ../match-service
docker build -t match-service:1.0 -f Dockerfile.match .
cd ../ui-app
docker build -t ui-app:1.0 -f Dockerfile.ui .
cd ../api-gateway
docker build -t api-gateway:1.0 -f Dockerfile.gateway .
