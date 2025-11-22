package com.patrones.comportamiento.observer;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers(InventoryEvent event);
}