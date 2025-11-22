package com.patrones.comportamiento.strategy;

import lombok.Setter;

public class InventoryCountingSession {

    @Setter
    private CountingStrategy strategy;

    public int countProduct(String productId) {
        return strategy.count(productId);
    }
}
