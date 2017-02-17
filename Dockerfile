FROM java:8-jdk

COPY build/distributions/user-service.zip /usr/lib/adscoop/user-service.zip

RUN apt-get update 

RUN cd /usr/lib/adscoop/ && unzip user-service.zip
RUN rm /usr/lib/adscoop/user-service.zip

EXPOSE 8181
