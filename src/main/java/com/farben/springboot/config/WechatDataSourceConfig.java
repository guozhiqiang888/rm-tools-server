package com.farben.springboot.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = WechatDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "wechatSqlSessionTemplate")
public class WechatDataSourceConfig {

    /**
     * dao层的包路径
     */
    static final String PACKAGE = "com.farben.springboot.mapper";

    /**
     * mapper文件的相对路径
     */
    private static final String MAPPER_LOCATION = "classpath*:mybatis/mapper/wechat/*.xml";

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.wechat")
    @Primary
    public DataSource wechatDataSource(){
        return  new DruidDataSource();
    }

    @Bean
    @Primary
    public SqlSessionFactory wechatSqlSessionFactory(@Qualifier("wechatDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(WechatDataSourceConfig.MAPPER_LOCATION));
        return bean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager wechatTransactionManager(@Qualifier("wechatDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate wechatSqlSessionTemplate(@Qualifier("wechatSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
