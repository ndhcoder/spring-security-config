package com.ndhcoder.demo.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/configuration/**","/swagger**","/webjars/**","/v2/**", "/swagger-resources/**").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/users/register**").permitAll()
                .anyRequest()
                .authenticated();
    }
}
