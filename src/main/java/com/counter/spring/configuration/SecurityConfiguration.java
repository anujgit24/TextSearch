package com.counter.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.counter.spring.security.FailureBasicAuthicationEntrypoint;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    private static String REALM="MY_TEST_REALM";
     
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("anuj").password("password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("john").password("password").roles("USER");
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
  
      http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/counter-api/**").hasRole("ADMIN")
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
     
   
    public FailureBasicAuthicationEntrypoint getBasicAuthEntryPoint(){
        return new FailureBasicAuthicationEntrypoint();
    }
     

}