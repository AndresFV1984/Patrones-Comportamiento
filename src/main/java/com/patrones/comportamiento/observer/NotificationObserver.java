package com.patrones.comportamiento.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationObserver implements Observer {
    @Override
    public void update(InventoryEvent event) {
        if (event.getType() == EventType.DIFFERENCE_DETECTED || event.getType() == EventType.INCONSISTENCY_DETECTED) {
            log.warn("[Notifications] Alertando supervisores/coordinadores: sesión {} | {}",
                    event.getSessionId(), event.getMessage());
            // TODO: Enviar notificación (email, SMS, webhook, Teams/Slack)
        } else {
            log.debug("[Notifications] Evento no crítico para alertar: {}", event.getType());
        }
    }
}
