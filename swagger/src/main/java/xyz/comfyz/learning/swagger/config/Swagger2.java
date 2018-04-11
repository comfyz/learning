package xyz.comfyz.learning.swagger.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Author:      宗康飞
 * Mail:        zongkangfei@sudiyi.cn
 * Date:        14:52 2018/3/19
 * Version:     1.0
 * Description:
 */
@EnableSwagger2 //启用swagger
@Configuration  //注入spring
public class Swagger2 {

    /**
     * 创建API应用
     * {@link Swagger2#apiInfo()} 增加API相关信息
     * 通过{@link Docket#select()}函数返回一个{@link ApiSelectorBuilder}实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用扫描带{@link Api}注解的类(也可指定扫描的包路径来定义指定要建立API的目录)。
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .extensions(Collections.singletonList(new StringVendorExtension("项目地址", "")))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://{$ip}:{$port}/basePath/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多请参考官网地址https://swagger.io/")
                .contact(new Contact("comfyz", "", "comfyz@qq.com"))
                .version("1.0")
                .build();
    }
}
