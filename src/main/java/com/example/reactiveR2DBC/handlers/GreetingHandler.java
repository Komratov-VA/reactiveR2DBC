package com.example.reactiveR2DBC.handlers;

import com.example.reactiveR2DBC.domain.Message;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        Long start = request.queryParam("start")
                .map(Long::valueOf)
                .orElse(0L);
        Long count = request.queryParam("count")
                .map(Long::valueOf)
                .orElse(3L);
        Flux<Message> data =
                Flux
                .just(
                        "Hello, reactive!",
                "More one",
                "Thied post",
                "Fourth post"
                ).skip(start)
                        .take(count)
                .map(Message::new);
//        BodyInserter<String, ReactiveHttpOutputMessage> body =
//                BodyInserters.fromValue("Hello, Spring!");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest request){

            String user = request.queryParam("user")
                    .orElse("Nobody");
            return ServerResponse.ok().
//                           contentType(MediaType.TEXT_PLAIN)
        render("index", Map.of("user",user));
//                                    BodyInserters.fromValue("Main Spring")
    }
}
