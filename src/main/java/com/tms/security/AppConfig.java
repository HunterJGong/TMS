package com.tms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.tms.web.*" })
@Import({ SecurityConfiguration.class })
public class AppConfig {
//
//	@Bean(name = "dataSource")
//	public DriverManagerDataSource dataSource() {
//	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//	    driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test");
//	    driverManagerDataSource.setUsername("tms");
//	    driverManagerDataSource.setPassword("p4ssw0rd");
//	    return driverManagerDataSource;
//	}
//	
//	@Bean
//	public InternalResourceViewResolver viewResolver() {
//	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//	    viewResolver.setViewClass(JstlView.class);
//	    viewResolver.setPrefix("/WEB-INF/pages/");
//	    viewResolver.setSuffix(".jsp");
//	    return viewResolver;
//	}
//	
}