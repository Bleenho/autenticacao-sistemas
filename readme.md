# Steps to start
- Create and configure file application.properties
- Execute Script.sql in the database that was configured in the file application.properties
- Generate jar`$ mvn clean package` 
- Run jar`$ java -jar target\autenticacao.jar`
  

## Required properties
- server.context-path=/autenticacao
- spring.datasource.url=jdbc:mysql://[server]:3306/autenticacao?useTimezone=true&serverTimezone=UTC
- spring.datasource.username=[user]
- spring.datasource.password=[pass]


http://localhost:8080/autenticacao/swagger-ui.html