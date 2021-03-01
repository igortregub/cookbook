#!/bin/bash

echo "The application will start in ${SLEEP_TIME}s..." && sleep ${SLEEP_TIME}

exec "$@"