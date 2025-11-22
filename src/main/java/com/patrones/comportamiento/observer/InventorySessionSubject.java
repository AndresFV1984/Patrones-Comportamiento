package com.patrones.comportamiento.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
public class InventorySessionSubject implements Subject {

    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    @Override
    public void attach(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
            log.info("Observer attached: {}", o.getClass().getSimpleName());
        }
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
        log.info("Observer detached: {}", o != null ? o.getClass().getSimpleName() : "null");
    }

    @Override
    public void notifyObservers(InventoryEvent event) {
        log.info("Notifying {} observers for event: {} - sessionId={}",
                observers.size(), event.getType(), event.getSessionId());
        observers.forEach(o -> {
            try {
                o.update(event);
            } catch (Exception ex) {
                log.error("Observer {} failed handling event {}: {}",
                        o.getClass().getSimpleName(), event.getType(), ex.getMessage(), ex);
            }
        });
    }

    // Punto de entrada para la sesión: registra un evento y dispara notificaciones
    public void registerEvent(InventoryEvent event) {
        notifyObservers(event);
    }
}
