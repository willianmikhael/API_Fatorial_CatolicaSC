package com.example.fatorial.model;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class FatorialCalculator {

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public FatorialCalculator(ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Mono<Integer> calcularFatorial(int numero) {
        if (numero < 0) {
            return Mono.just(-1);
        } else if (numero == 0 || numero == 1) {
            return Mono.just(1);
        } else {
            String cacheKey = "fatorial:" + numero;
            return redisTemplate.opsForValue().get(cacheKey)
                    .flatMap(resultado -> Mono.just(Integer.parseInt(resultado)))
                    .switchIfEmpty(
                            Mono.defer(() -> {
                                int fatorial = 1;
                                for (int i = 1; i <= numero; i++) {
                                    fatorial *= i;
                                }
                                return redisTemplate.opsForValue().set(cacheKey, String.valueOf(fatorial))
                                        .thenReturn(fatorial);
                            })
                    );
        }
    }
}
