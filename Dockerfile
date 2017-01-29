FROM java:8-jdk-alpine

COPY build/distributions/user-service.zip /usr/lib/adscoop/user-service.zip

RUN apk update && apk upgrade
RUN apk add bash && screen

RUN cd /usr/lib/adscoop/ && unzip user-service.zip
RUN rm /usr/lib/adscoop/user-service.zip

RUN screen -dmS user-service /usr/lib/adscoop/user-service/bin/user-service