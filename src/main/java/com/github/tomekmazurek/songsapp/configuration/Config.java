package com.github.tomekmazurek.songsapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@ComponentScan("com.github.tomekmazurek.songsapp")
public class Config {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.tomekmazurek.songsapp.controller"))
                .paths(PathSelectors.regex("^(?!/(error).*$).*$"))
                .build()
                .apiInfo(apiMetaData());
    }

    private ApiInfo apiMetaData() {
        return new ApiInfo(
                "Songs-app Rest API",
                "All Api's available for songs-app",
                "1.0", "terms",
                new Contact(
                        "Tomasz Mazurek",
                        "",
                        "mazurek.tomasz.a@gmail.com"),
                "license",
                "url",
                Collections.emptyList());
    }
}
