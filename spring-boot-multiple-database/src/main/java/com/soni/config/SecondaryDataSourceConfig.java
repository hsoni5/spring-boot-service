package com.soni.config;

import com.soni.config.properties.SecondaryDataSourceProperties;
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
        entityManagerFactoryRef = "secondaryEntityManager",
        transactionManagerRef = "secondaryTransactionManager",
        basePackages = DatabaseConstant.SECONDARY_ENTITY_PACKAGE
)
public class SecondaryDataSourceConfig {
    private SecondaryDataSourceProperties secondaryDataSourceProperties;

    @Autowired
    public SecondaryDataSourceConfig(SecondaryDataSourceProperties secondaryDataSourceProperties) {
        this.secondaryDataSourceProperties = secondaryDataSourceProperties;
    }

    @Bean
    protected DataSource secondaryDatabase() {
        return new HikariDataSource(secondaryDataSourceProperties);
    }

    private Properties jpaPrimaryDatabaseProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", secondaryDataSourceProperties.getHbm2ddl());
        properties.setProperty("hibernate.dialect", secondaryDataSourceProperties.getDialect());
        properties.setProperty("hibernate.show_sql", secondaryDataSourceProperties.getShowSql());
        return properties;
    }

    @Bean(name = "secondaryEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(secondaryDatabase());
        entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManager.setPackagesToScan(DatabaseConstant.SECONDARY_ENTITY_PACKAGE);
        entityManager.setPersistenceUnitName(DatabaseConstant.SECONDARY);
        entityManager.setJpaProperties(jpaPrimaryDatabaseProperties());
        return entityManager;
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("secondaryEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}