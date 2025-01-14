package org.project.urltester.minipostman.WebConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfiguration {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
