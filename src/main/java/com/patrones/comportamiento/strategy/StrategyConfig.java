package com.patrones.comportamiento.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyConfig {

    @Bean
    public CountingStrategy manualStrategy() {
        return new ManualCountingStrategy();
    }

    @Bean
    public CountingStrategy barcodeStrategy() {
        return new BarcodeCountingStrategy();
    }

    @Bean
    public CountingStrategy rfidStrategy() {
        return new RfidCountingStrategy();
    }

    @Bean
    public InventoryCountingSession inventoryCountingSession() {
        return new InventoryCountingSession();
    }

    @Bean
    public CountProductUseCase countProductUseCase(InventoryCountingSession session) {
        return new CountProductUseCase(session);
    }
}