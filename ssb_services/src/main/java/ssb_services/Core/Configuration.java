
package ssb_services.Core;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class Configuration 
{
    public static EntityManagerFactory getEntityManagerFactory(Environment environment) 
    {
        try
        {
            DataSource ds = DataSourceBuilder.create()
                .driverClassName(environment.getProperty("db.driver"))
                .url(environment.getProperty("db.url"))
                .username(environment.getProperty("db.user"))
                .password(environment.getProperty("db.password"))
                .build();
            
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(ds);
            em.setPackagesToScan(environment.getProperty("lib.entities"));

            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);

            Properties properties = new Properties();
            properties.setProperty("hibernate.dialect", 
                environment.getProperty("db.dialect"));
            properties.setProperty("hibernate.enable_lazy_load_no_trans",
                environment.getProperty("db.lazy_load_no_trans"));
            properties.setProperty("hibernate.show_sql", 
                environment.getProperty("db.show_sql"));
            em.setJpaProperties(properties);
            em.afterPropertiesSet(); 
             
            EntityManagerFactory emf = em.getObject();
            return emf;
        }
        catch (Exception ex)
        {
            return null;
        }        
    }
}
