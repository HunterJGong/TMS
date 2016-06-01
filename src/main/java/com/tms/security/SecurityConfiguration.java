package com.tms.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
/*
	@Autowired
	DataSource mydataSource;*/
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configAuth");
	  auth
	  	.jdbcAuthentication()
	  		.usersByUsernameQuery(getUserQuery())
	  			.authoritiesByUsernameQuery(getAuthoritiesQuery());
	 }
	
	//Pull Username and password by login username
	private String getUserQuery() {
		return "SELECT u_username as username, u_password as password "
                + "FROM tms_user "
                + "WHERE u_username = ?";
	}
	//Pull user's role
	private String getAuthoritiesQuery() {
		 return "select u_username as username, ur_role as athority "
		 		+ "from tms_user, TMS_USER_ROLES "
		 		+ "where u_username=? "
		 		+ "and u_role = ur_id";
	}

	

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		 http
         .csrf()
             .disable()
         .authorizeRequests()
             .antMatchers("/resources/**").permitAll()
             .antMatchers("/developer/**").access("hasRole('ROLE_developer')")
             .antMatchers("/manager/**").access("hasRole('manager')")
             .antMatchers("/rest/**").anonymous()
             .antMatchers("/app/logout").permitAll()
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/login").permitAll()
             .loginProcessingUrl("/loginProcess").permitAll()
           //  .usernameParameter("username")
           //  .passwordParameter("password")
           //  .successHandler(new CustomAuthenticationSuccessHandler())
             //.failureHandler(new CustomAuthenticationFailureHandler())
             .and()
         .logout()
             .logoutUrl("/logout")
             .logoutSuccessUrl("/login").permitAll()
		 .and()
		 	.exceptionHandling().accessDeniedPage("/login");
    }
	
	
	

}