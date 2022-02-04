FROM registry.access.redhat.com/ubi8/openjdk-11-runtime:1.10

USER root

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' TZ='Asia/Jakarta'

RUN microdnf update && \
    microdnf install tzdata libXrender libXext fontconfig  && \
    ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && \
    microdnf clean all

COPY wkhtml/wkhtmltopdf /usr/local/bin/

EXPOSE 8080
USER 185

COPY target/app.jar /deployments/app.jar
ENTRYPOINT [ "java", "-jar", "/deployments/app.jar" ]