package com.farben.readme.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;
import com.farben.readme.util.JasyptUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = WechatDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "wechatSqlSessionTemplate")
public class WechatDataSourceConfig {

    /**
     * dao package (relative path)
     */
    static final String PACKAGE = "com.farben.readme.mapper";

    /**
     * mapper file (relative path)
     */
    private static final String MAPPER_LOCATION = "classpath*:mybatis/mapper/wechat/*.xml";

    @Value("${spring.datasource.wechat.username}")
    private String username;

    @Value("${spring.datasource.wechat.password}")
    private String password;

    @Value("${spring.datasource.wechat.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.wechat.url}")
    private String url;

    @Value("${spring.datasource.wechat.type}")
    private String type;

    @Value("${spring.datasource.wechat.initialSize}")
    private Integer initialSize;

    @Value("${spring.datasource.wechat.minIdle}")
    private Integer minIdle;

    @Value("${spring.datasource.wechat.maxActive}")
    private Integer maxActive;

    @Value("${spring.datasource.wechat.maxWait}")
    private Integer maxWait;

    @Value("${spring.datasource.wechat.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.wechat.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${spring.datasource.wechat.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.wechat.testWhileIdle}")
    private Boolean testWhileIdle;

    @Value("${spring.datasource.wechat.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${spring.datasource.wechat.testOnReturn}")
    private Boolean testOnReturn;

    @Value("${spring.datasource.wechat.poolPreparedStatements}")
    private Boolean poolPreparedStatements;

    @Value("${spring.datasource.wechat.filters}")
    private String filters;

    @Value("${spring.datasource.wechat.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.wechat.useGlobalDataSourceStat}")
    private Boolean useGlobalDataSourceStat;

    @Value("${spring.datasource.wechat.connectionProperties}")
    private String connectionProperties;

    @Bean
    @Primary
    public DataSource wechatDataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(JasyptUtil.decrypt(password));
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
//        dataSource.setDbType(type);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setFilters(filters);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        dataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        if (!StringUtils.isEmpty(connectionProperties)) {
            Properties connectProperties = new Properties();
            String[] propertiesList = connectionProperties.split(";");
            for (String str : propertiesList) {
                String[] obj = str.split("=");
                String key = obj[0];
                String val = obj[1];
                connectProperties.put(key, val);
            }
            dataSource.setConnectProperties(connectProperties);
        }
        return dataSource;
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
