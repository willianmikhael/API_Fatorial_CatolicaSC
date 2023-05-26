package com.example.fatorial.model;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SuperFatorialCalculator {

    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final FatorialCalculator fatorialCalculator;

    public SuperFatorialCalculator(ReactiveRedisTemplate<String, String> redisTemplate,
                                   FatorialCalculator fatorialCalculator) {
        this.redisTemplate = redisTemplate;
        this.fatorialCalculator = fatorialCalculator;
    }

    public Mono<Integer> calcularSuperFatorial(int numero) {
        if (numero < 0) {
            return Mono.just(-1);
        } else if (numero == 0 || numero == 1) {
            return Mono.just(1);
        } else {
            String cacheKey = "super_fatorial:" + numero;
            return redisTemplate.opsForValue().get(cacheKey)
                    .flatMap(resultado -> Mono.just(Integer.parseInt(resultado)))
                    .switchIfEmpty(
                            Mono.defer(() -> {
                                return fatorialCalculator.calcularFatorial(numero)
                                        .flatMap(fatorial -> calcularSuperFatorial(numero - 1)
                                                .map(superFatorial -> superFatorial * fatorial))
                                        .flatMap(result -> redisTemplate.opsForValue().set(cacheKey, String.valueOf(result))
                                                .thenReturn(result));
                            })
                    );
        }
    }
}
