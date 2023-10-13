package lk.ijse.spring.config;

import lk.ijse.spring.repo.FileUploadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {FileUploadRepo.class})
@PropertySource("classpath:application.properties")
public class JPAConfig {

    @Autowired
    Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory (DataSource ds, JpaVendorAdapter vad){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(ds);
        factory.setJpaVendorAdapter(vad);
        factory.setPackagesToScan(environment.getRequiredProperty("pro.entity"));
        return factory;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUsername(environment.getRequiredProperty("pro.username"));
        ds.setPassword(environment.getRequiredProperty("pro.password"));
        ds.setDriverClassName(environment.getRequiredProperty("pro.driver"));
        ds.setUrl(environment.getRequiredProperty("pro.url"));
        return ds;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setDatabase(Database.MYSQL);
        va.setGenerateDdl(true);
        va.setDatabasePlatform(environment.getRequiredProperty("pro.dial"));
        va.setShowSql(true);
        return va;
    }


}
