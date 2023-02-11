package com.example.hayatbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.hayatbackend.shared.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class HayatbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HayatbackendApplication.class, args);
	}
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder (){
        return new BCryptPasswordEncoder();
        
    }
    
    @Bean
    public SpringApplicationContext springApplicationContext(){
        return new SpringApplicationContext();
    }


}
