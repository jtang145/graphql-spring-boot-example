package cn.naches.examples.graphql.pets.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfiguration {

    @Bean("newPetThreadPool")
    public ExecutorService newPetThreadPool() {
        return Executors.newSingleThreadExecutor();
    }
}
