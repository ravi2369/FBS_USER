package com.fbs.user.config;

import com.fbs.user.exceptions.FBSException;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebFluxConfig {


    @Bean
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
    }
}
