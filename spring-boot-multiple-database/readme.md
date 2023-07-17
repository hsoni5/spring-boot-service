> # Spring JPA – Multiple Databases
 * Spring boot allows you to connect to multiple databases by configuring multiple data sources in a single spring boot application using hibernate and JPA.
 * Spring boot enables repositories to connect to multiple databases using JPA from a single application. Multiple data source configurations allow multiple database connections to be established in a spring boot application.
 * With application properties file configurations, spring boot makes it very easy to use multiple databases in a single spring boot application.
 * Each database connection should have its own DataSource, EntityManagerFactory, and PlatformTransactionManager. To connect multiple databases, each database should be configured in its own spring boot configuration file. Multiple data sources should be configured for multiple databases.
# Application properties details
<p>
The application properties will be placed in the resources folder’s application.properties files. The application.properties now has two database configurations. The hibernate JPA related configurations also added in the application.properties file.
</p>

> # Primary
spring.datasource.jdbc-url=jdbc:postgresql://localhost:5432/primary
spring.datasource.username=postgres
spring.datasource.password=root123
spring.datasource.connectionTimeout=3000
spring.datasource.idleTimeout=60000
spring.datasource.maxLifetime=300000
spring.datasource.minimumIdle=5
spring.datasource.maximumPoolSize=20
spring.datasource.leakDetectionThreshold=3000
spring.datasource.hbm2ddl=create
spring.datasource.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.showSql=true
> # Secondary
spring.datasource.secondary.jdbc-url=jdbc:postgresql://localhost:5432/secondary
spring.datasource.secondary.username=postgres
spring.datasource.secondary.password=root123
spring.datasource.secondary.connectionTimeout=3000
spring.datasource.secondary.idleTimeout=60000
spring.datasource.secondary.maxLifetime=300000
spring.datasource.secondary.minimumIdle=5
spring.datasource.secondary.maximumPoolSize=20
spring.datasource.secondary.leakDetectionThreshold=3000
spring.datasource.secondary.hbm2ddl=create
spring.datasource.secondary.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.secondary.showSql=true
># Mongo DB
spring.data.mongodb.host=ROOSW42SLPC7942.ind.zensar.com
spring.data.mongodb.port=27017
spring.data.mongodb.database==multi-service
spring.data.mongodb.username=admin
spring.data.mongodb.password=root123
spring.data.mongodb.socketTimeout = 1000
spring.data.mongodb.connectTimeout = 1000


