package com.learn.springboot.practice.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @ClassName LearnDataSourceConfig
 * @Description:learn数据库实例连接配置
 * @Author lfq
 * @Date 2020/4/22
 **/
@Configuration
@MapperScan(basePackages = {"com.learn.springboot.practice.dao.learn"}, sqlSessionFactoryRef = "learnSqlSessionFactory")
public class LearnDataSourceConfig {
    @Autowired
    @Qualifier("learnDataSource")
    private DataSource learnDataSource;

    @Bean(name = "learnSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(learnDataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/learn/*.xml"));
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "learnSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
