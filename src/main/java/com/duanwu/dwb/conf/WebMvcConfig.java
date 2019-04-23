package com.duanwu.dwb.conf;

import com.duanwu.dwb.base.annotation.AwesomeParamMethodArgumentResolver;
import com.duanwu.dwb.base.annotation.MatchPathVariableMethodArgumentResolver;
import com.duanwu.dwb.base.converter.ObjectHttpMessageConverter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${is_underscore_param_name}")
    boolean isUnderscoreName;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        AwesomeParamMethodArgumentResolver resolver = new AwesomeParamMethodArgumentResolver();
        resolver.setLowerUnderscoreName(isUnderscoreName);
        argumentResolvers.add(resolver);
        argumentResolvers.add(new MatchPathVariableMethodArgumentResolver());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectHttpMessageConverter converter = new ObjectHttpMessageConverter();
        if (isUnderscoreName) {
            converter.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }
        converters.add(converter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        //TIPS: consider limit CROS in production env
        registry.addMapping("/**")
                .allowedMethods("GET", "HEAD", "PUT", "DELETE", "POST", "PATCH")
                .allowedOrigins("*");
    }
}
