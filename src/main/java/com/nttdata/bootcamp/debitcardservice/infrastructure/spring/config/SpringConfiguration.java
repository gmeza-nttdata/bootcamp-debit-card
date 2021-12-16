package com.nttdata.bootcamp.debitcardservice.infrastructure.spring.config;

import com.nttdata.bootcamp.debitcardservice.application.repository.DebitCardRepository;
import com.nttdata.bootcamp.debitcardservice.infrastructure.repository.DebitCardCrudRepository;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SpringConfiguration {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public DebitCardRepository repository() {
        return new DebitCardCrudRepository();
    }

}
