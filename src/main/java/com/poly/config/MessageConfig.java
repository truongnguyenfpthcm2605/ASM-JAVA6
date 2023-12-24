package com.poly.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;
@Configuration
public class MessageConfig implements WebMvcConfigurer {

    @Bean("messageSource")
    MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setBasenames("classpath:messages/account_vi");
        return ms;

    }

    @Bean("localeResolver")
    LocaleResolver getLocaleResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        // first Show
        resolver.setDefaultLocale(new Locale("vi"));
        // all server
        resolver.setCookiePath("/");
        // Date
        resolver.setCookieMaxAge(10 * 24 * 60 * 60);
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor loChangeInterceptor = new LocaleChangeInterceptor();
        // variable language
        loChangeInterceptor.setParamName("lang");
        // Interceptor Access and Block
        registry.addInterceptor(loChangeInterceptor).addPathPatterns("/**").excludePathPatterns("/assets/img/**");
    }
}
