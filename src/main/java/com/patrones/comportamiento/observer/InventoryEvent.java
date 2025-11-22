package com.patrones.comportamiento.observer;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class InventoryEvent {
    private EventType type;
    private String sessionId;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, Object> payload; // datos adicionales (ej. itemId, expectedQty, countedQty, etc.)
}