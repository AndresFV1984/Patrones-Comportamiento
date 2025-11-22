package com.patrones.comportamiento.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReconciliationObserver implements Observer {
    @Override
    public void update(InventoryEvent event) {
        log.info("[Reconciliation] Recalculando diferencias para sesión {} con datos: {}",
                event.getSessionId(), event.getPayload());
        // TODO: Recalcular diferencias y preparar información para decisiones correctivas
    }
}
