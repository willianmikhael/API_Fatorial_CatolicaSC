package com.example.fatorial.controller;

import com.example.fatorial.model.FatorialCalculator;
import com.example.fatorial.model.SuperFatorialCalculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;
@Controller
public class FatorialController {

    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final FatorialCalculator fatorialCalculator;
    private final SuperFatorialCalculator superFatorialCalculator;

    public FatorialController(ReactiveRedisTemplate<String, String> redisTemplate,
                              FatorialCalculator fatorialCalculator,
                              SuperFatorialCalculator superFatorialCalculator) {
        this.redisTemplate = redisTemplate;
        this.fatorialCalculator = fatorialCalculator;
        this.superFatorialCalculator = superFatorialCalculator;
    }

    @GetMapping("/")
    public String calculatorForm(Model model) {
        model.addAttribute("fatorialResult", 0);
        return "index";
    }


    @PostMapping("/")
    public Mono<String> obterCalculos(@RequestParam("numfatorial") int numfatorial, Model model) {
        Mono<Integer> fatorialMono = fatorialCalculator.calcularFatorial(numfatorial);
        Mono<Integer> superFatorialMono = superFatorialCalculator.calcularSuperFatorial(numfatorial);

        return Mono.zip(fatorialMono, superFatorialMono)
                .flatMap(tuple -> {
                    int fatorialResult = tuple.getT1();
                    int superFatorialResult = tuple.getT2();

                    model.addAttribute("numfatorial", numfatorial);
                    model.addAttribute("fatorialResult", fatorialResult);
                    model.addAttribute("superFatorialResult", superFatorialResult);

                    return Mono.just("index");
                });
    }
}

