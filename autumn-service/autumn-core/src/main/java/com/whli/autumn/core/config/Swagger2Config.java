package com.whli.autumn.core.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>API文档配置类</p>
 * @author whli
 * @datea 2019/1/3 11:26
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    // 定义分隔符,配置Swagger多包
    private static final String splitor = ";";

    @Value("${swagger.base-package}")
    private String swaggerPackage;

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Autumn Admin API")
                .contact(new Contact("whli","http://blog.whli745.com/","623374047@qq.com"))
                .version("1.0.0")
                .description("一套SpringBoot WEB脚手架")
                .build();
    }

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage(swaggerPackage))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 重写basePackage方法，使能够实现多包访问
     * @author  teavamc
     * @date 2019/1/26
     * @param basePackage
     * @return com.google.common.base.Predicate<springfox.documentation.RequestHandler>
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }
}
