# Steps to start
- Create and configure file application.properties
- Execute Script.sql in the database that was configured in the file application.properties
- Run in commandLine  `$ mvn clean package` to generate jar and run `$ java -jar target\autenticacao.jar`
  

## Required properties
- server.context-path=/autenticacao
- spring.datasource.url=jdbc:mysql://[server]:3306/[base]?useTimezone=true&serverTimezone=UTC
- spring.datasource.username=[user]
- spring.datasource.password=[pass]