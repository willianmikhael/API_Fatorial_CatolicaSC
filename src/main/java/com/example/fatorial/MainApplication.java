package com.example.fatorial;



import com.example.fatorial.model.FatorialCalculator;
import com.example.fatorial.model.SuperFatorialCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class MainApplication {

	private final ReactiveRedisTemplate<String, String> redisTemplate;
	private final FatorialCalculator fatorialCalculator;
	private final SuperFatorialCalculator superFatorialCalculator;

	public MainApplication(ReactiveRedisTemplate<String, String> redisTemplate,
						   FatorialCalculator fatorialCalculator,
						   SuperFatorialCalculator superFatorialCalculator) {
		this.redisTemplate = redisTemplate;
		this.fatorialCalculator = fatorialCalculator;
		this.superFatorialCalculator = superFatorialCalculator;
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@GetMapping("/fatorial/{numero}")
	public Mono<ResponseEntity<String>> obterFatorial(@PathVariable int numero) {
		return fatorialCalculator.calcularFatorial(numero)
				.map(resultado ->
						ResponseEntity.ok()
								.header("Content-Type", "application/json")
								.body("{\"numero\":" + numero + ",\"fatorial\":" + resultado + "}")
				);
	}

	@GetMapping("/super_fatorial/{numero}")
	public Mono<ResponseEntity<String>> obterSuperFatorial(@PathVariable int numero) {
		return superFatorialCalculator.calcularSuperFatorial(numero)
				.map(resultado ->
						ResponseEntity.ok()
								.header("Content-Type", "application/json")
								.body("{\"numero\":" + numero + ",\"super_fatorial\":" + resultado + "}")
				);
	}
}



