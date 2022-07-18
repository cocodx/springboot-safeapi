package com.lg.safeapi.config;

import com.lg.safeapi.plugins.SqlPlugin;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author 正能量导师
 * @date 2022/6/15-9:47
 * @Description
 */
@Configuration
@MapperScan(basePackages = {"com.lg.safeapi.mapper.second"},sqlSessionFactoryRef = "sqlSessionFactory2")
public class SecondDataSourceConfig {

    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.datasource2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource2")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
        //由于自定义配置sqlSessionFactory导致plugin失效
        bean.setPlugins(new SqlPlugin());
        //测试设置配置文件
        bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/config/mybatis-config.xml"));
        return bean.getObject();
    }
    



}
