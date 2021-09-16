package me.mhlee.reactiveredisexam.domain;

import reactor.core.publisher.Flux;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeController {
    private final ReactiveRedisOperations<String, Coffee> coffeeOps;

    CoffeeController(ReactiveRedisOperations<String, Coffee> coffeeOps) {
        this.coffeeOps = coffeeOps;
    }

    @GetMapping("/coffees")
    public Flux<Coffee> all() {
        return coffeeOps.keys("*")
                .flatMap(it -> coffeeOps.opsForValue().get(it));
    }
}
