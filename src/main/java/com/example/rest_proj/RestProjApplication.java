package com.example.rest_proj;

import com.example.rest_proj.config.SpringAppApplicationContext;
import com.example.rest_proj.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@Import({SwaggerConfig.class})
public class RestProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestProjApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SpringAppApplicationContext springAppApplicationContext(){return new SpringAppApplicationContext();}

}
