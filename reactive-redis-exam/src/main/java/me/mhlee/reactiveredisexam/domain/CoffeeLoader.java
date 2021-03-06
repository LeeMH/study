package me.mhlee.reactiveredisexam.domain;

import reactor.core.publisher.Flux;

import java.util.UUID;
import javax.annotation.PostConstruct;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

@Component
public class CoffeeLoader {
    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, Coffee> coffeeOps;

    public CoffeeLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Coffee> coffeeOps) {
        this.factory = factory;
        this.coffeeOps = coffeeOps;
    }

    @PostConstruct
    public void loadData() {

        factory.getReactiveConnection().serverCommands()
                .flushAll()
                .log()
                .thenMany(
                        Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
                                .log()
                                .map(name -> new Coffee(UUID.randomUUID().toString(), name))
                                .flatMap(coffee -> coffeeOps.opsForValue().set(coffee.getId(), coffee))
                )
                .thenMany(
                        coffeeOps.keys("*")
                                .log()
                                .flatMap(coffeeOps.opsForValue()::get)
                )
                .subscribe(System.out::println);
    }
}
