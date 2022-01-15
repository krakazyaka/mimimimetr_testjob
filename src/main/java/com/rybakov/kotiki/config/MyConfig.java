package com.rybakov.kotiki.config;


import com.rybakov.kotiki.repository.KotikRepository;
import com.rybakov.kotiki.services.Implement.GeneratePairService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@RequiredArgsConstructor
@ComponentScan("com.rybakov.kotiki")
public class MyConfig {

    private final KotikRepository kotikRepository;
    @Bean
    @SessionScope

    public GeneratePairService sessionScopedBean(KotikRepository kotikRepository) {

        return new GeneratePairService(kotikRepository);
    }
}
