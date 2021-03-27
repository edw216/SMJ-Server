package com.experiencers.server.smj.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfigurer extends WebMvcConfigurationSupport {

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("세상의 모든 자취생 API")
                .description("API 문서 사용법 : JWT토큰을 우측 Authorize의 value값에 넣고 Authorize 버튼을 누르신 후 사용하시면 됩니다.")
                .build();
    }
    @Bean
    public Docket api() {
        List<ResponseMessage> responseMessages =new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder()
                .code(400)
                .message("잘못된 요청")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(401)
                .message("접근 권한 없음")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(404)
                .message("찾을 수 없음")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(500)
                .message("내부 서버 오류")
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .useDefaultResponseMessages(false)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.experiencers.server.smj.api"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .globalResponseMessage(RequestMethod.GET,responseMessages)
                .globalResponseMessage(RequestMethod.POST,responseMessages)
                .globalResponseMessage(RequestMethod.PUT,responseMessages)
                .globalResponseMessage(RequestMethod.DELETE,responseMessages);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        //                .setCacheControl(CacheControl.maxAge(3L, TimeUnit.HOURS).cachePublic())
        //                .resourceChain(true);
        registry.
                addResourceHandler( "swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.
                addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
    private ApiKey apiKey(){
        return new ApiKey("JWT","Authorization","header");
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT",authorizationScopes));
    }
}
