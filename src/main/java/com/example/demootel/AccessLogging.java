package com.example.demootel;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
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
        log.info(">> requestUri=[{}]", exchange.getRequest().getURI());
        return chain.filter(exchange)
                .doOnSuccess(result -> logSuccessResult(log, exchange))
                .doOnError(throwable -> logErrorResult(log, exchange, throwable));
    }

    @Override
    public int getOrder() {
        return SecurityWebFiltersOrder.FIRST.getOrder();
    }

    private void logSuccessResult(Logger log, ServerWebExchange exchange) {
        log.info("<< Service successfully returns result, statusCode=[{}]", exchange.getResponse().getStatusCode());
    }

    private void logErrorResult(Logger log, ServerWebExchange exchange, Throwable throwable) {
        log.error("<< Service error result msg=[{}]", throwable.getMessage());
    }
}