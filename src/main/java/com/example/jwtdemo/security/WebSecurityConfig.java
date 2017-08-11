package com.example.jwtdemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String ADMIN_USER = "admin";
  private static final String ADMIN_PWD = "testMe";
  private static final String ADMIN_ROLE = "ADMIN";


  /**
   * Here, we have decided that:
   *    - everyone can access the / route.
   *    - the /login route is only publicly available for POST requests.
   *    - For all other routes, authentication is required.
   * @param httpSecurity
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        // We filter the api/login requests
        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
        // And filter other requests to check for the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    // Create a default account
    authenticationManagerBuilder.inMemoryAuthentication()
        .withUser(ADMIN_USER)
        .password(ADMIN_PWD)
        .roles(ADMIN_ROLE);
  }
}
