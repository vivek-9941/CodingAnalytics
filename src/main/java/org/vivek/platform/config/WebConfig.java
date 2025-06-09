package org.vivek.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {
    @Bean
    public WebClient CodeforcesClient()
    {
        return WebClient.builder().baseUrl("https://codeforces.com/api").build();

    }

    @Bean
    public WebClient CodeChefClient(){
        return WebClient.builder().baseUrl("https://codechef-api.vercel.app/handle").build();
    }

    @Bean
    public WebClient leetcodeWebclient(){
        return WebClient.builder().baseUrl("https://leetcode.com").build();
    }
}
