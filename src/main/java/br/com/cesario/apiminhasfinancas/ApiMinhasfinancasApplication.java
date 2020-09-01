package br.com.cesario.apiminhasfinancas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class ApiMinhasfinancasApplication{

    public static void main(String[] args) {
        SpringApplication.run(ApiMinhasfinancasApplication.class, args);
    }

}
