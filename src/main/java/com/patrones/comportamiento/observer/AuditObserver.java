package com.patrones.comportamiento.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuditObserver implements Observer {
    @Override
    public void update(InventoryEvent event) {
        log.info("[Audit] Registrando evento {} para sesión {}: {}",
                event.getType(), event.getSessionId(), event.getMessage());
        // TODO: Persistir en bitácora/auditoría (tabla audit_events)
    }
}