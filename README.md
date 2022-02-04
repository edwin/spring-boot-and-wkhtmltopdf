# About this Repo

A containerized Spring Boot web application to generate PDF report by using wkhtmltopdf.   

How To
------------
Build by using mvn
```
$ mvn clean package
```

Docker build
```
$ docker build -t springboot-and-pdf .
```

Docker run
```
$  docker run -p 8080:8080  springboot-and-pdf
```

Libraries
------------
- Java 11
- Wkhtmltopdf 0.12.4
- Snowdrop 2.4.9.Final-redhat-00001


How To
------------
Make sure to download wkhtmltox-0.12.4, and extract the content of `bin` folder into `wkhtml` before doing a Docker build so the project structure would looks pretty much like this.  
```
+--- .gitignore
+--- Dockerfile
+--- pom.xml
+--- src
|   +--- main
|   |   +--- java
|   |   |   +--- com
|   |   |   |   +--- edw
|   |   |   |   |   +--- controllers
|   |   |   |   |   |   +--- HelloWorldController.java
|   |   |   |   |   |   +--- ReportController.java
|   |   |   |   |   +--- Main.java
|   |   +--- resources
|   |   |   +--- application.properties
|   |   |   +--- template.html
|   +--- test
|   |   +--- java
+--- wkhtml
    +--- wkhtmltopdf
```