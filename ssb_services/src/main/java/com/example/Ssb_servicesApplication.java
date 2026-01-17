
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "lib_domain.Entities")
@ComponentScan(basePackages = { 
    "lib_repositories.Interfaces", 
    "lib_repositories.Implementations", 
    "ssb_services.Implementations" })
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Ssb_servicesApplication 
{
    public static void main(String[] args) 
    {
        SpringApplication.run(Ssb_servicesApplication.class, args);
    }
}
