package com.tz;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Jerry on 2015/9/24.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration  //开启自动配置
@EnableAspectJAutoProxy//AOP的切面功能
@EnableTransactionManagement
@EnableJpaRepositories //表明Spring Data JPA遍历任何实现了org.springframework.data.repository.Repository接口的接口，然后自动生成它的实现类。
@PropertySource("classpath:application.properties") //配置数据库参数
public class AppConfig {
    /*@Bean
    public DataSource getDataSource(){
        BasicDataSource ds=new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/tz-business");
        ds.setDriverClassName("org.gjt.mm.mysql.Driver");
        ds.setPassword("root");
        ds.setUsername("root");
        return ds;
    }
    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean lsfb=new LocalSessionFactoryBean();
        lsfb.setDataSource(getDataSource());
        Properties prop=new Properties();
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.put("hibernate.show_sql", "true");
        lsfb.setHibernateProperties(prop);
        lsfb.setPackagesToScan("com.tz.entity");
        return lsfb;
    }

    @Bean
    public HibernateTransactionManager getTM(){
        HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setSessionFactory(getSessionFactory().getObject());
        return tm;
    }*/

}

