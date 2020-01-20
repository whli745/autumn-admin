package com.whli.autumn.core.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * <p>主数据源配置</p>
 * @author whli
 * @version 1.0.0
 * @since 2019/9/9 14:38
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
@Data
public class PrimaryDataSourceConfig {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;


    @Bean
    @Primary
    public DataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMaximumPoolSize(60);
        return dataSource;
    }
/*
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        // 设置mybatis的xml所在位置
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/primary/*Mapper.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }*/

    /**
     * 注入事务，在 serviceImpl 的时候使用
     * @return
     */
    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}
