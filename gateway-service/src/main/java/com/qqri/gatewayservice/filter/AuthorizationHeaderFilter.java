//package com.qqri.gatewayservice.filter;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
//@Component
//@RequiredArgsConstructor
//public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
//
//    private final JwtUtil jwtUtil;
//
//    public AuthorizationHeaderFilter() {
//        super(Config.class);
//    }
//
//    public static class Config{
//
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            String token = exchange.getRequest().getHeaders().get("Authorization").get().substring(7);
//            Map<String, Object> userInfo = jwtUtil.getUserParseInfo(token);
//
//            addAuthorizationHeaders(exchange.getRequest(), userInfo);
//
//            return chain.filter(exchange);
//        };
//    }
//
//    private void addAuthorizationHeaders(ServerHttpRequest request, Map<String, Object> userInfo) {
//        request.mutate()
//                .header("X-Authorization-Id" , userInfo.get("memberId").toString())
//                .build();
//    }
//
//    @Bean
//    public ErrorWebExceptionHandler implements ErrorWebExceptionHandler {
//        private String getErrorCode(int errorCode) {
//            return "{\\"errorCode\\":" + errorCode + "}";
//
//        }
//
//        @Override
//        public Mono<Void> handle(
//                ServerWebExchange exchange, Throwable ex) {
//            int errorCode = 500;
//            if (ex.getClass() == NullPointerException.class) {
//                errorCode = 100;
//            } else if (ex.getClass() == ExpiredJwtException.class) {
//                errorCode = 200;
//            }
//
//            byte[] bytes = getErrorCode(errorCode).getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//            return exchange.getResponse().writeWith(Flux.just(buffer));
//
//        }
//    }
//}
