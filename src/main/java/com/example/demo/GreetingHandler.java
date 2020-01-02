package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(fromObject("Hello, Spring!"));
    }

    public Mono<ServerResponse> hello2(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(fromObject("Hello, Spring 2!"));
    }

    public Mono<ServerResponse> calledForm(ServerRequest request) {
        GreetingWebClient gwc = new GreetingWebClient();
        return gwc.getResult()
                .flatMap(s ->
                        gwc.getOtherResult().flatMap(
                                t -> ServerResponse
                                        .ok().contentType(MediaType.TEXT_PLAIN)
                                        .body(fromObject(t+"&"+s))));
    }


}

