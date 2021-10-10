package com.practise.article.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * swagger를 사용하기 위한 설정
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Docket : api에 대한 자세한 정보를 담는다.
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())     // Request Content-Type 에 대한 설정 (Optional)
                .produces(getProduceContentTypes())     // Response Content-Type 에 대한 설정 (Optional)
                .apiInfo(getApiInfo())                  // Swagger API 문서에 대한 설명을 설정 (Optional)
                .select()
                .apis(RequestHandlerSelectors.any())    // Swagger API 문서로 만들기 원하는 BasePackage 경로 설정. (Required)
                .paths(PathSelectors.ant("/**"))        // Swagger API 문서로 만들 URL 설정. (Required)
                .build();
    }
    /**
     * Request Content-Type 에 대한 설정 (Optional)
     * @return
     */
    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }
    /**
     * Response Content-Type 에 대한 설정 (Optional)
     * @return
     */
    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
    /**
     * Swagger API 문서에 대한 설명을 표기하는 메소드 (Optional)
     * @return
     */
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Article API")
                .description("Article CRUD API")
                .contact(new Contact("[Swagger]", "https://github.com/kkyehit/Spring-OAuth2", "~~~"))
                .version("1.0")
                .build();
    }  
}
