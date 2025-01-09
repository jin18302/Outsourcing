package com.example.outsourcing.config;

import com.example.outsourcing.common.aspect.PurchasesAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {


    @Bean
    public PurchasesAspect orderAspect(){
        return new PurchasesAspect();
    }
}