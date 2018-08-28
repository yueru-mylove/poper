package com.sport.peking.poper.config;

import com.sport.peking.poper.config.datasource.MybatisConfig;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author miracle~
 * create at 2018-08-28 11:28
 */
@Configuration
@AutoConfigureAfter(MybatisConfig.class)
public class MapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.sport.peking.poper.mapper");
        return mapperScannerConfigurer;
    }

}
