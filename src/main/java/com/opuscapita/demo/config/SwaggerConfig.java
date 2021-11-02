package com.opuscapita.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Profile("!swagger")
    @Bean
    public Docket apiDocsDisabled(AppSettings settings) {
        ApiInfo apiInfo = new ApiInfoBuilder()
            .title("NONE")
            .description("Disabled")
            .version("")
            .build();
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .select().paths(PathSelectors.none()).apis(RequestHandlerSelectors.none())
            .build();
    }

    @Profile("swagger")
    @Bean
    public Docket apiDocsEnabled(AppSettings settings) {
        ApiInfo apiInfo = new ApiInfoBuilder()
            .title(settings.getAppName())
            .description("## A brief description of this API here...")
            .version("some.version")
            .build();

        Set<String> contentTypes = new HashSet<>();
        contentTypes.add("application/json");

        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .enableUrlTemplating(false)
            .useDefaultResponseMessages(false)
            .produces(contentTypes)
            .consumes(contentTypes)
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.opuscapita.demo"))
            .paths(PathSelectors.any())
            .build();
    }
}
