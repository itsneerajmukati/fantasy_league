set PATH=%PATH%D:\Softwares\node-v14.16.1-win-x64;D:\Softwares\java\jdk11.0.11_9\bin;D:\Softwares\apache-maven-3.8.1\bin
set JAVA_HOME=D:\Softwares\java\jdk11.0.11_9
cd D:\DemoProjects\fantasy_league\user-service
mvn spring-boot:run -Dspring-boot.run.arguments='--spring.cloud.bootstrap.enabled=true'