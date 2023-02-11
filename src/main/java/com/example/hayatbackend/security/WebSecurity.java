/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.security;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.hayatbackend.service.UserService;




/**
 *
 * @author hayat
 */

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
     
        http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL,"/users/create")
                .permitAll().antMatchers(HttpMethod.GET,"/users/downloadFile/**").permitAll()
                .antMatchers(HttpMethod.GET,"/projects","/projects").permitAll()
                .antMatchers(HttpMethod.POST,"/projets/create").permitAll()
                .antMatchers(HttpMethod.POST,"/projects","/projects/create","/projects/getProjetsById","projects/*","projects/change").permitAll()
                .antMatchers(HttpMethod.DELETE,"/projects/*").permitAll()
                .antMatchers(HttpMethod.POST,"/projects/change").permitAll()
                .antMatchers(HttpMethod.POST,"/usersproject/notin","/usersproject/in").permitAll()
                .antMatchers(HttpMethod.POST,"/project_users").permitAll()
                .antMatchers(HttpMethod.POST,"/project_users/getpro").permitAll()
                .antMatchers(HttpMethod.POST,"/project_users/delete").permitAll()
                .antMatchers(HttpMethod.POST,"/categories").permitAll()
                .antMatchers(HttpMethod.GET,"/categories").permitAll()
                .antMatchers(HttpMethod.POST,"/categories/get").permitAll()
                 .antMatchers(HttpMethod.POST,"/users/login").permitAll()
                .anyRequest().authenticated().and()
                .addFilter(getAuthenticationFilter())//authentication filter
                .addFilter(new AuthenticationFilter(authenticationManager()))
               //authorization filter
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       
        
        
        
        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
   /* @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	   CorsConfiguration configuration = new CorsConfiguration();
           configuration.setAllowedOrigins(Arrays.asList("*"));
           configuration.setAllowedMethods(Arrays.asList("GET","POST"));
     
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/projets", configuration);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

  
    public AuthenticationFilter getAuthenticationFilter () throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/users/login");
        return authenticationFilter;
    }
    
}
