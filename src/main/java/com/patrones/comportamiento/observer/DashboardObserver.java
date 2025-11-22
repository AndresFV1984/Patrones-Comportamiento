package com.patrones.comportamiento.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DashboardObserver implements Observer {
    @Override
    public void update(InventoryEvent event) {
        log.info("[Dashboard] Actualizando métricas para sesión {}: {} | payload={}",
                event.getSessionId(), event.getMessage(), event.getPayload());
        // TODO: Actualizar KPIs/indicadores en cache/base de datos
    }
}
