FROM java:8-jdk-alpine

COPY build/distributions/user-service.zip /usr/lib/adscoop/user-service.zip

RUN unzip /usr/lib/adscoop/user-service.zip

RUN rm /usr/lib/adscoop/user-service.zip

RUN sh /usr/lib/adscoop/user-service/bin/user-service