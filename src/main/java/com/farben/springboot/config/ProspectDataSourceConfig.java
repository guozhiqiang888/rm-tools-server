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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = ProspectDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "prospectSqlSessionTemplate")
public class ProspectDataSourceConfig {

    /**
     * dao层的包路径
     */
    static final String PACKAGE = "com.farben.springboot.mapper.prospect";

    /**
     * mapper文件的相对路径
     */
    private static final String MAPPER_LOCATION = "classpath*:mybatis/mapper/prospect/*.xml";

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.prospect")
    public DataSource prospectDataSource(){
        return  new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory prospectSqlSessionFactory(@Qualifier("prospectDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ProspectDataSourceConfig.MAPPER_LOCATION));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager prospectTransactionManager(@Qualifier("prospectDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate prospectSqlSessionTemplate(@Qualifier("prospectSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
