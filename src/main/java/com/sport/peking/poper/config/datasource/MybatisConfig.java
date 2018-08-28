package com.sport.peking.poper.config.datasource;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author miracle~
 * create at 2018-08-28 10:45
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(environment.getProperty("spring.datasource.url"));
        druidDataSource.setUsername(environment.getProperty("spring.datasource.username"));
        druidDataSource.setPassword(environment.getProperty("spring.datasource.password"));
        druidDataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        druidDataSource.setKeepAlive(true);
        druidDataSource.setMinIdle(0);
        druidDataSource.setMaxActive(50);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setInitialSize(5);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setPoolPreparedStatements(false);
        return druidDataSource;
    }


    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        /* 设置typeAlias 包扫描路径 */
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.type-aliases-package"));
        //添加XML目录
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
