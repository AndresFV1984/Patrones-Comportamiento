package com.patrones;

import com.patrones.comportamiento.observer.EventType;
import com.patrones.comportamiento.observer.InventoryEvent;
import com.patrones.comportamiento.observer.InventorySessionSubject;
import com.patrones.comportamiento.strategy.CountProductUseCase;
import com.patrones.comportamiento.strategy.CountingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@Slf4j
@SpringBootApplication
public class PatronesApplication {

    private final CountProductUseCase useCase;
    private final Map<String, CountingStrategy> strategies;
    private final InventorySessionSubject subject;

    public PatronesApplication(CountProductUseCase useCase, Map<String, CountingStrategy> strategies, InventorySessionSubject subject) {
        this.useCase = useCase;
        this.strategies = strategies;
        this.subject = subject;
    }

    public static void main(String[] args) {
		SpringApplication.run(PatronesApplication.class, args);
	}


    @EventListener(ApplicationReadyEvent.class)
    private void inicializar() {
        executeStrategy();
        executeObserver();
    }

    public void executeStrategy() {

        System.out.println("\n========================================");
        System.out.println("     EJECUCIÓN DE TODAS LAS ESTRATEGIAS  ");
        System.out.println("========================================\n");
        strategies.forEach((strategyName, strategyBean) -> {
            // Generar productId diferente por estrategia
            String productId = generateProductId(strategyName);
            int result = useCase.execute(productId, strategyBean);
            System.out.println("   Estrategia: " + strategyName);
            System.out.println("   Product ID usado: " + productId);
            System.out.println("   Resultado del conteo: " + result);
            System.out.println("----------------------------------------");
        });

        System.out.println("                FIN DEL PROCESO");
        System.out.println("========================================\n");
    }

    public void executeObserver(){
        // Evento 1: Diferencia detectada
        InventoryEvent event1 = InventoryEvent.builder()
                .type(EventType.DIFFERENCE_DETECTED)
                .sessionId("SES-2025-11-18-001")
                .message("Diferencia detectada en el item SKU-12345")
                .timestamp(LocalDateTime.now())
                .payload(Map.of(
                        "itemId", "SKU-12345",
                        "expectedQty", 100,
                        "countedQty", 92,
                        "location", "Bodega-ALT-02"
                ))
                .build();

        subject.registerEvent(event1);

        // Evento 2: Cierre de conteo
        InventoryEvent event2 = InventoryEvent.builder()
                .type(EventType.COUNT_CLOSED)
                .sessionId("SES-2025-11-18-001")
                .message("Conteo finalizado correctamente")
                .timestamp(LocalDateTime.now())
                .payload(Map.of("totalItems", 500))
                .build();

        System.out.println("=== INICIO DE EJECUCIÓN DE EVENTOS ===");

        subject.registerEvent(event2);
        System.out.println("\n>>> Ejecutando Evento 1: " + event1.getType());
        subject.registerEvent(event1);
        System.out.println(">>> Evento 1 procesado correctamente.\n");

        System.out.println(">>> Ejecutando Evento 2: " + event2.getType());
        subject.registerEvent(event2);
        System.out.println(">>> Evento 2 procesado correctamente.\n");

        System.out.println("=== FIN DE EJECUCIÓN DE EVENTOS ===");
    }

    private String generateProductId(String strategyName) {
        String cleanName = strategyName.replace("Strategy", "")
                .replace("Counting", "")
                .toUpperCase();
        int random = new Random().nextInt(900) + 100;
        return cleanName + "-" + random;
    }
}



