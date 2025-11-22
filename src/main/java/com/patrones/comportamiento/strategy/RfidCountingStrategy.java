package com.patrones.comportamiento.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RfidCountingStrategy implements CountingStrategy {

    @Override
    public int count(String productId) {
        log.info("Conteo RFID ejecutado para {}", productId);
        return 20;
    }
}
