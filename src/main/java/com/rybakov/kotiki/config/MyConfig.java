package com.rybakov.kotiki.config;


import com.rybakov.kotiki.repository.KotikRepository;
import com.rybakov.kotiki.services.Implement.GeneratePairService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@RequiredArgsConstructor
@ComponentScan("com.rybakov.kotiki")
public class MyConfig {

    private final KotikRepository kotikRepository;
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public GeneratePairService sessionScopedBean(KotikRepository kotikRepository) {

        return new GeneratePairService(kotikRepository);
    }
}
