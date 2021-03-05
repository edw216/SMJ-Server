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
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfigurer extends WebMvcConfigurationSupport {

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("세상의 모든 자취생 API")
                .description("Smj Server Api Document ")
                .build();
    }
    @Bean
    public Docket api() {
        List<ResponseMessage> responseMessages =new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder()
                .code(200)
                .message("성공")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(201)
                .message("작성됨")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(400)
                .message("잘못된 접근")
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
                .message("서버 내부 에러")
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(this.apiInfo())
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
}
