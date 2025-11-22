package com.patrones.comportamiento.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManualCountingStrategy implements CountingStrategy {

    @Override
    public int count(String productId) {
        log.info("Conteo manual ejecutado para {}", productId);
        return 1;
    }
}
