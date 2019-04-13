package com.shnu.part.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableWebMvc
@EnableSwagger2
@EnableAspectJAutoProxy //开启自动代理
@ComponentScan(value = "com.scenery.web") // 必须在此种类中开启包扫描,否则无效
public class WebConfig implements WebMvcConfigurer {


    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        // 开启静态资源的请求转发到默认servlet上,不配置页面报错404,(默认servlet不是DispatcherServlet!理解的)
        configurer.enable();

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*");

    }
    /**
     * 配置静态资源 配置static 映射到 classpath:/static/下 即Resources/static/
     ** @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(31556926);

        /**
         * 1. 配置swagger的映射
         * 2. 配置swagger 的jar包映射
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     *  配置视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;

    }

    /**
     * 配置swaager2
     *  @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shnu.part.web.api"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    /**
     *  页面的一些配置
     * @return
     */
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Travel Rest API",
                "",
                "1.0.0",
                "",
                new Contact("chenlang", "", "molichangyan@163.com"),
                "",
                "");
        return apiInfo;
    }

}
