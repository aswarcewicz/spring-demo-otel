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
public class AccessLogging implements OrderedWebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             WebFilterChain chain) {
        return Mono.justOrEmpty("")
                .doOnNext(e -> log.info(">> requestUri=[{}]", exchange.getRequest().getURI()))
                .flatMap(z -> chain.filter(exchange))
                .doOnSuccess(result -> log.info("<< Service successfully returns result, statusCode=[{}]", exchange.getResponse().getStatusCode()))
                .doOnError(throwable -> log.error("<< Service error result msg=[{}]", throwable.getMessage()))
                .contextWrite(ReactorBaggage.append("user_id", "RANDOMVALUE"));
    }

    @Override
    public int getOrder() {
        return SecurityWebFiltersOrder.FIRST.getOrder();
    }
}