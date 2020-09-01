package br.com.cesario.apiminhasfinancas.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket agendaAPI() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.cesario.apiminhasfinancas"))
                .build()
                .apiInfo(informacoes());
    }

    private ApiInfo informacoes() {
        ApiInfo apiInfo = new ApiInfo("Minhas Finanças - API - By Cesário Pereira Neto",
                "API Rest de cadastro de multiplos usuários e de transações financeiras",
                "1.0",
                "Terms of Service",
                new Contact("Cesário Pereira Neto",
                        "https://linkedin.com/in/cesarionto/",
                        "cesariopereiraneto@gmail.com"),
                "Apache License 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
        return apiInfo;
    }
}

