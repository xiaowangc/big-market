//package com.chige.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * @author wangyc
// * @date 2022/10/28
// */
//@Configuration
//@MapperScan(basePackages = "com.chige.infrastructure.persistent.dao", sqlSessionFactoryRef = "bigMarketSqlSessionFactory", sqlSessionTemplateRef = "bigMarketSqlSessionTemplate")
//public class BigMarketDBConfig {
//
//    @Bean(name = "bigMarketDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource buildDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "bigMarketSqlSessionFactory")
//    public SqlSessionFactory bigMarketSqlSessionFactory(@Qualifier("bigMarketDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*_mapper.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    /**
//     * 配置事务管理
//     */
//    @Bean(name = "bigMarketTransactionManager")
//    public DataSourceTransactionManager transactionManager(@Qualifier("bigMarketDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "bigMarketSqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("bigMarketSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//}
