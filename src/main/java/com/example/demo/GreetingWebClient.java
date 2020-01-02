package com.example.demo;


import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GreetingWebClient {
    private WebClient client = WebClient.create("http://localhost:8080");

    private Mono<ClientResponse> result1 = client.get()
            .uri("/hello")
            .accept(MediaType.TEXT_PLAIN)
            .exchange();

    private Mono<ClientResponse> result2 = client.get()
            .uri("/hello2")
            .accept(MediaType.TEXT_PLAIN)
            .exchange();


    public Mono<String> getResult() {
        return result1.flatMap(res -> res.bodyToMono(String.class));
    }

    public Mono<String> getOtherResult() {
        return result2.flatMap(res -> res.bodyToMono(String.class));
    }
}
