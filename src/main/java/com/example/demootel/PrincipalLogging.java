package com.example.demootel;

import io.micrometer.tracing.contextpropagation.reactor.ReactorBaggage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.filter.OrderedWebFilter;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PrincipalLogging implements OrderedWebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             WebFilterChain chain) {
        return Mono.justOrEmpty("")
                .doOnNext(e -> log.info(">> appending user_id"))
                .flatMap(z -> chain.filter(exchange))
                .contextWrite(ReactorBaggage.append("user_id", "MockedUserId"));
    }

    @Override
    public int getOrder() {
        return SecurityWebFiltersOrder.SECURITY_CONTEXT_SERVER_WEB_EXCHANGE.getOrder() + 1;
    }
}