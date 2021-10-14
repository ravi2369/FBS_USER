package com.fbs.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebFluxConfig {

    private static String adminUrl;
    @Autowired
    private WebClient webClient;

    public WebFluxConfig(@Value("${admin.url}") String adminUrl) {
        this.adminUrl = adminUrl;
    }

   /* @Bean
    public WebClient webClient() {
        WebClient.Builder wbBuilder = WebClient.builder();

        try {
            SslContext sslContext = SslContextBuilder
                    .forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();

            HttpClient httpClient = HttpClient.create().secure(sslSpec -> sslSpec.sslContext(sslContext));
            ClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);
            wbBuilder.clientConnector(httpConnector);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FBSException(e.getMessage());
        }

        WebClient webClient = wbBuilder.build();
        return webClient;
    }*/

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(adminUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
