package com.patrones.comportamiento.strategy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CountProductUseCase {

    private final InventoryCountingSession session;

    public int execute(String productId, CountingStrategy strategy) {
        session.setStrategy(strategy);
        return session.countProduct(productId);
    }
}