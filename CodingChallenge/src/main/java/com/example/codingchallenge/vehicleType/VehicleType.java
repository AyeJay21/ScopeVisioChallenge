package com.example.codingchallenge.vehicleType;

// Definierte Werte für Fahrzuegtypen
public enum VehicleType {
    CAR(0.1),
    LIMOUSINE(0.3),
    SUV(0.3),
    VAN(0.15),
    COUPE(0.2),
    CABRIO(0.2),
    SMALLCAR(0.05);

    public final double factor;

    VehicleType(double factor){
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}
