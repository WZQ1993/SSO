#!/bin/bash
java -jar service_a.jar --spring.config.name=db,application --spring.profiles.active=dev >>app.log