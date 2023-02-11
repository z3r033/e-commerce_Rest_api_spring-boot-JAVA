/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.security;


import com.example.hayatbackend.SpringApplicationContext;
import com.example.hayatbackend.service.UserService;
import com.example.hayatbackend.shared.dto.UserDto;
import com.example.hayatbackend.ui.model.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author hayat
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
    private final AuthenticationManager authenticationManager ;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       
        try {
            UserLoginRequestModel cred   = new ObjectMapper()
                    .readValue(request.getInputStream(), UserLoginRequestModel.class);
            
            return authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            cred.getEmail(), cred.getPassword(), new ArrayList<>()) );
        } catch (IOException ex) {
        throw new RuntimeException();
        }
        
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
      String userName  = ((User) authResult.getPrincipal()).getUsername();
      String token = Jwts.builder()
              .setSubject(userName)
              .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
              .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
              .compact();
      
      UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
      UserDto userDto =  userService.getUser(userName);
      response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX +token);
      response.addHeader("UserID", userDto.getUserId());
      response.addHeader("Access-Control-Allow-Origin", "*");
      String text= "{\"idUser\":\""+userDto.getUserId()+"\",\"firstName\":\""+userDto.getFirstName()+"\",\"lastName\":\""+userDto.getLastName()+"\",\"password\":\""+userDto.getPassword()+"\",\"email\":\""+userDto.getEmail()+"\",\"telephone\":\""+userDto.getTelephone()+"\",\"image_profile\":\""+userDto.getImage_profile()+"\"}";
      response.setContentType("application/json");
      response.getWriter().write(text);
      response.getWriter().flush();
  
              
    }
                
    
    
}
    
