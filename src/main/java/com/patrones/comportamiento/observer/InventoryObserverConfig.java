package com.patrones.comportamiento.observer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InventoryObserverConfig {

    private final InventorySessionSubject subject;
    private final List<Observer> observers;

    @PostConstruct
    public void attachObservers() {
        observers.forEach(subject::attach);
    }
}
