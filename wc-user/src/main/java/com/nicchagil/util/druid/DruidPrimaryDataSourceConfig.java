package com.nicchagil.util.druid;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DruidPrimaryDataSourceConfig {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DruidPrimaryDataSourceConfigProperties druidPrimaryDataSourceConfigProperties;

    @Bean
    public DataSource primaryDataSource (){
        DruidDataSource datasource = new DruidDataSource();
        /* 基础配置 */
        datasource.setUrl(this.druidPrimaryDataSourceConfigProperties.getUrl());
        datasource.setUsername(this.druidPrimaryDataSourceConfigProperties.getUsername());
        datasource.setPassword(this.druidPrimaryDataSourceConfigProperties.getPassword());
        datasource.setDriverClassName(this.druidPrimaryDataSourceConfigProperties.getDriverClassName());

        /* 其他配置 */
        datasource.setInitialSize(this.druidPrimaryDataSourceConfigProperties.getInitialSize());
        datasource.setMinIdle(this.druidPrimaryDataSourceConfigProperties.getMinIdle());
        datasource.setMaxActive(this.druidPrimaryDataSourceConfigProperties.getMaxActive());
        datasource.setMaxWait(this.druidPrimaryDataSourceConfigProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(this.druidPrimaryDataSourceConfigProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(this.druidPrimaryDataSourceConfigProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(this.druidPrimaryDataSourceConfigProperties.getValidationQuery());
        datasource.setTestWhileIdle(this.druidPrimaryDataSourceConfigProperties.getTestWhileIdle());
        datasource.setTestOnBorrow(this.druidPrimaryDataSourceConfigProperties.getTestOnBorrow());
        datasource.setTestOnReturn(this.druidPrimaryDataSourceConfigProperties.getTestOnReturn());
        
        this.logger.info("druidPrimaryDataSourceConfigProperties : {}", druidPrimaryDataSourceConfigProperties);
        this.logger.info("datasource : {}", datasource);

        return datasource;
    }

}
