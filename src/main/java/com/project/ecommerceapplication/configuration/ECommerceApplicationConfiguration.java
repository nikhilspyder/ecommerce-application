package com.project.ecommerceapplication.configuration;

import com.project.ecommerceapplication.filter.RequestLoggingFilter;
import com.project.ecommerceapplication.filter.ResponseLoggingFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.project.ecommerceapplication.*")
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "com.project.ecommerceapplication.respository")
public class ECommerceApplicationConfiguration {

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/*"); // Apply filter to all URLs
        return registrationBean;
    }

    @Bean
    @Qualifier("ResponseLoggingFilter")
    public FilterRegistrationBean<ResponseLoggingFilter> customResponseLoggingFilter() {
        FilterRegistrationBean<ResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ResponseLoggingFilter());
        registrationBean.addUrlPatterns("/*"); // Apply filter to all URLs
        return registrationBean;
    }
}

