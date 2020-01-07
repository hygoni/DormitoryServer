package com.github.hygoni.dormitory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import net.rakugakibox.util.YamlResourceBundle;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class MessageConfiguration implements WebMvcConfigurer {

    //기본 언어 : KOREAN
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.KOREAN);
        return slr;
    }

    //parameter에 lang=XX이 들어간 경우 언어를 변경함
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    //application.yml에서 lang 폴더 위치, 인코딩을 불러옴
    @Bean
    public MessageSource messageSource(
            @Value("${spring.messages.basename}") String baseName,
            @Value("${spring.messages.encoding}") String encoding) {
        YamlMessageSource ms = new YamlMessageSource();
        ms.setBasename(baseName);
        ms.setDefaultEncoding(encoding);
        ms.setAlwaysUseMessageFormat(true);
        ms.setUseCodeAsDefaultMessage(true);
        ms.setFallbackToSystemLocale(true);
        return ms;
    }

    private static class YamlMessageSource extends ResourceBundleMessageSource {
        @Override
        protected ResourceBundle doGetBundle(String baseName, Locale locale) {
            return ResourceBundle.getBundle(baseName, locale, YamlResourceBundle.Control.INSTANCE);
        }
    }

}
