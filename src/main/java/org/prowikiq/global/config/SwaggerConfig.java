package org.prowikiq.global.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Class: SwaggerConfig Project: prowikiQ Package: org.prowikiq.global.config
 * <p>
 * Description: SwaggerConfig
 *
 * @author dong-hoshin
 * @date 4/27/24 19:40 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@EnableWebMvc
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport  {

    @Bean
    public Docket api(TypeResolver typeResolver) {
        return new Docket(DocumentationType.OAS_30) // 3.0 문서버전으로 세팅
            .useDefaultResponseMessages(true)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("org.prowikiq"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("ProWikiQ API")
            .description("ProWikiQ API Doc")
            .version("1.0")
            .build();
    }
}