#!/bin/bash

# Exit immediately if .env is missing
if [ ! -f ".env" ]; then
  echo "❌ .env file not found! Aborting."
  exit 1
fi

# Load .env file
set -o allexport
source .env
set +o allexport

# Set default profile if not provided
SPRING_PROFILE=${SPRING_PROFILE:-prod}

# Validate required .env variables
if [ -z "$SPRING_DATASOURCE_URL" ] || [ -z "$SPRING_DATASOURCE_USERNAME" ] || [ -z "$SPRING_DATASOURCE_PASSWORD" ]; then
  echo "❌ Missing required database variables in .env! Please set SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, and SPRING_DATASOURCE_PASSWORD."
  exit 1
fi

# Ensure SSL disable (Postgres servers sometimes reject SSL)
if [[ "$SPRING_DATASOURCE_URL" != *"sslmode="* ]]; then
  SPRING_DATASOURCE_URL="${SPRING_DATASOURCE_URL}?sslmode=disable"
fi

echo "✅ Starting Spring Boot app with profile: $SPRING_PROFILE"
echo "🔗 Database URL: $SPRING_DATASOURCE_URL"

# Run Spring Boot with environment variables passed as JVM system properties
./mvnw spring-boot:run \
  -Dspring.profiles.active=$SPRING_PROFILE \
  -Dspring.datasource.url=$SPRING_DATASOURCE_URL \
  -Dspring.datasource.username=$SPRING_DATASOURCE_USERNAME \
  -Dspring.datasource.password=$SPRING_DATASOURCE_PASSWORD
