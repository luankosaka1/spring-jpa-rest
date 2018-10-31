package br.com.devmedia.curso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class SpringJpaConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test?createDatabaseifNotExist=true&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean f = new LocalContainerEntityManagerFactoryBean();
        f.setDataSource(dataSource());
        f.setPackagesToScan("br.com.devmedia.curso.domain");
        f.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        f.setJpaProperties(jpaProperties());
        f.afterPropertiesSet();
        return f.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager m = new JpaTransactionManager();
        m.setEntityManagerFactory(entityManagerFactory());
        m.setJpaDialect(new HibernateJpaDialect());
        return m;
    }

    private Properties jpaProperties() {
        Properties p = new Properties();
        p.setProperty("hibernate.show_sql","true");
        p.setProperty("hibernate.format_sql", "true");
        p.setProperty("hibernate.hbm2ddl.auto", "update");
        return p;
    }
}
