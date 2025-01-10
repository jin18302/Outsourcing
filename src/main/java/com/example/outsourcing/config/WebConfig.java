package com.example.outsourcing.config;

import com.example.outsourcing.common.aspect.PurchasesAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAspectJAutoProxy
public class WebConfig  {


    @Bean
    public PurchasesAspect orderAspect(){
        return new PurchasesAspect();
    }
}