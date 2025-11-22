package com.patrones.comportamiento.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BarcodeCountingStrategy implements CountingStrategy {

    @Override
    public int count(String productId) {
        log.info("Conteo con lector de barras ejecutado para {}", productId);
        return 5;
    }
}
