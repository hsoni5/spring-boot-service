package com.soni.config;

import com.soni.config.properties.DefaultDataSourceProperties;
import com.soni.constant.DatabaseConstant;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManager",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = "com.soni"
)
public class DefaultDataSourceConfig {
    private DefaultDataSourceProperties defaultDataSourceProperties;

    @Autowired
    public DefaultDataSourceConfig(DefaultDataSourceProperties defaultDataSourceProperties) {
        this.defaultDataSourceProperties = defaultDataSourceProperties;
    }
    @Bean
    @Primary
    protected DataSource primaryDatabase() {
        return new HikariDataSource(defaultDataSourceProperties);
    }

    private Properties jpaPrimaryDatabaseProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", defaultDataSourceProperties.getHbm2ddl());
        properties.setProperty("hibernate.dialect", defaultDataSourceProperties.getDialect());
        properties.setProperty("hibernate.show_sql", defaultDataSourceProperties.getShowSql());
        return properties;
    }

    @Primary
    @Bean(name = "primaryEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(primaryDatabase());
        entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManager.setPackagesToScan(DatabaseConstant.PRIMARY_ENTITY_PACKAGE);
        entityManager.setPersistenceUnitName(DatabaseConstant.PRIMARY);
        entityManager.setJpaProperties(jpaPrimaryDatabaseProperties());
        return entityManager;
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("primaryEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
